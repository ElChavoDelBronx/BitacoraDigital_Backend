package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import mx.edu.utez.bitacoradigitalservices.modules.subtasks.dtos.SubtaskDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dto.TaskUpdateDto;
import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.SaveTaskDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.UpdateTaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse> findTaskByProject(@PathVariable("projectId") Long projectId){
        return taskService.findTaskByProject(projectId);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse> findTaskByStudent(@PathVariable("studentId") Long studentId) {
        return taskService.findTaskByStudent(studentId);
    }
    @GetMapping("/in-progress/{studentId}")
    public ResponseEntity<ApiResponse> findInProgressTasks(@PathVariable("studentId") Long studentId) {
        return taskService.findInProgressTasks(studentId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findTaskById(@PathVariable("id") Long id) {
        return taskService.findTasksById(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveTask(@RequestBody SaveTaskDTO dto){
        return taskService.saveTask(dto);
    }
    @PostMapping("/save-subtask")
    public ResponseEntity<ApiResponse> saveSubtask(@RequestBody SubtaskDTO dto){
        return taskService.saveSubtask(dto);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody UpdateTaskDTO dto){
        return taskService.updateTask(dto);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable("id") Long id, @RequestBody TaskUpdateDto request) {
        taskService.updateTaskStatusAndHours(id, request);
        ApiResponse response = new ApiResponse("Tarea actualizada exitosamente", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable("id") Long id){
        return taskService.deleteTask(id);
    }
}