package mx.edu.utez.bitacoradigitalservices.modules.tasks;
import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.SaveTaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){

        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findTaskByProject(Long projectId){
        ApiResponse response = new ApiResponse(
                "Tareas obtenidas correctamente",
                taskRepository.findByProjectId(projectId),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findTasksById(Long id){
        ApiResponse response;

        Task found = taskRepository.findById(id).orElse(null);

        if(found != null){
            response = new ApiResponse(
                    "Tarea encontrada.",
                    found,
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

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> saveTask(SaveTaskDTO dto){
        ApiResponse response;

        try{
            Task task = new Task();

            task.setNameTask(dto.nameTask());
            task.setDescription(dto.description());
            task.setDueDate(dto.dueDate());
            task.setStatus(dto.status());

            Task saved = taskRepository.save(task);
            response = new ApiResponse(
                    "Tarea creada correctamente",
                    saved,
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
    public ResponseEntity<ApiResponse> updateTask(SaveTaskDTO dto){
        ApiResponse response;

        try {
            Task existing = taskRepository.findById(dto.id()).orElse(null);
            if(existing != null){
                existing.setNameTask(dto.nameTask());
                existing.setDescription(dto.description());
                existing.setDueDate(dto.dueDate());
                existing.setStatus(dto.status());
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
}
