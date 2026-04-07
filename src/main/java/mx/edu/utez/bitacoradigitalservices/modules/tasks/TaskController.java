package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.dto.TaskUpdateDto;
import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.SaveTaskDTO;
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

    @PutMapping("")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody SaveTaskDTO dto){
        return taskService.updateTask(dto);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @RequestBody TaskUpdateDto request) {
        try {
            String result = taskService.updateTaskStatusAndHours(id, request);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable("id") Long id){
        return taskService.deleteTask(id);
    }
}