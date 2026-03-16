package mx.edu.utez.bitacoradigitalservices.modules.tasks;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable("id") Long id){
        return taskService.deleteTask(id);
    }
}
