package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.projects.ProjectRepository;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.dtos.SubtaskDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.*;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.utils.TaskUtils;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dto.TaskUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findTaskByProject(Long projectId){
        ApiResponse response;
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        if(tasks.isEmpty()){
            response = new ApiResponse(
                    "Tareas no encontradas",
                    true,
                    HttpStatus.NOT_FOUND
            );
        } else {
            List<TaskBoardDTO> boardTasks = TaskUtils.entityListToBoardDTO(tasks);
            response = new ApiResponse(
                    "Tareas obtenidas correctamente",
                    boardTasks,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findTaskByStudent(Long studentId){
        ApiResponse response;
        List<Task> found = taskRepository.findAllByStudentId(studentId);
        if(found.isEmpty()){
            response = new ApiResponse(
                    "Tareas no encontradas",
                    true,
                    HttpStatus.NOT_FOUND
            );
        } else {
            List<TaskSummaryDTO> summary = TaskUtils.entityListToSummaryDTO(found);
            response = new ApiResponse(
                    "Tareas obtenidas correctamente",
                    summary,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findInProgressTasks(Long studentId){
        ApiResponse response;
        List<BasicTaskProjection> tasks = taskRepository.findInProgressTasks(studentId);
        if(tasks.isEmpty()){
            response = new ApiResponse(
                    "Tareas no encontradas",
                    true,
                    HttpStatus.NOT_FOUND
            );
        } else {
            response = new ApiResponse(
                    "Tareas obtenidas correctamente",
                    tasks,
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findTasksById(Long id){
        ApiResponse response;
        Task found = taskRepository.findById(id).orElse(null);

        if(found != null){
            response = new ApiResponse(
                    "Tarea encontrada.",
                    TaskUtils.entityToDetailedTaskDTO(found),
                    HttpStatus.OK
            );
        } else {
            response = new ApiResponse(
                    "Tarea no encontrada",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    public String updateTaskStatusAndHours(Long taskId, TaskUpdateDto request) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setStatus(request.getStatus());

            if ("Completada".equalsIgnoreCase(request.getStatus().toString()) && request.getLoggedHours() != null) {
                task.setLoggedHours(request.getLoggedHours());
            }

            taskRepository.save(task);
            return "Tarea actualizada exitosamente";
        }
        throw new RuntimeException("Tarea no encontrada");
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> saveTask(SaveTaskDTO dto){
        ApiResponse response;

        try{
            Project project = projectRepository.getReferenceById(dto.projectId());
            User student = userRepository.getReferenceById(dto.studentId());
            Task task = new Task();

            task.setNameTask(dto.nameTask());
            task.setDescription(dto.description());
            task.setDueDate(dto.dueDate());
            task.setStatus(TaskStatus.Pending);
            task.setProject(project);
            task.setStudent(student);

            Task saved = taskRepository.save(task);
            response = new ApiResponse(
                    "Tarea creada correctamente",
                    TaskUtils.entityToBoardDTO(saved),
                    HttpStatus.CREATED
            );
        } catch (Exception e){
            response = new ApiResponse(
                    "Error del servidor",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> deleteTask(Long id){
        ApiResponse response;
        try {
            taskRepository.deleteById(id);

            response = new ApiResponse(
                    "Tarea eliminada con exito",
                    true,
                    HttpStatus.OK
            );
        } catch (Exception e){
            response = new ApiResponse(
                    "Error interno del servidor",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> updateTask(UpdateTaskDTO dto){
        ApiResponse response;

        try {
            Task existing = taskRepository.findById(dto.id()).orElse(null);
            if(existing != null){
                if(dto.title() != null) existing.setNameTask(dto.title());
                if(dto.description() != null) existing.setDescription(dto.description());
                if(dto.dueDate() != null) existing.setDueDate(dto.dueDate());
                if(dto.status() != null) existing.setStatus(dto.status());
                if(dto.studentId() != null) existing.setStudent(userRepository.getReferenceById(dto.studentId()));

                Task saved = taskRepository.save(existing);
                response = new ApiResponse(
                        "Tarea actualizada correctamente",
                        saved,
                        HttpStatus.OK
                );
            } else {
                response = new ApiResponse(
                        "Tarea no encontrada",
                        true,
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e){
            response = new ApiResponse(
                    "Error interno del servidor",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    public ResponseEntity<ApiResponse> saveSubtask(SubtaskDTO dto) {
        ApiResponse response;
        try {
            Task task = taskRepository.findById(dto.idTask()).orElse(null);
            if(task != null ) {
                System.out.println("Titulo de subtarea: "+dto.name());
                SubTask subtask = new SubTask();
                subtask.setName(dto.name());
                subtask.setChecked(false);
                task.addSubtask(subtask);
                task = taskRepository.saveAndFlush(task);

                SubTask savedSubtask = task.getSubTask().stream().filter(s -> s.getName().equals(dto.name())).findFirst().get();

                response = new ApiResponse("Subtarea registrada con exito", savedSubtask.getId(), HttpStatus.CREATED);
            } else {
                response = new ApiResponse("Tarea correspondiente no encontrada", true, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}
