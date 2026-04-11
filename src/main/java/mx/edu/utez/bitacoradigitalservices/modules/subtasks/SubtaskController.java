package mx.edu.utez.bitacoradigitalservices.modules.subtasks;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.dtos.SubtaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subtasks")
public class SubtaskController {
    private final SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @PutMapping("/change-name")
    public ResponseEntity<ApiResponse> changeSubtaskName(@RequestBody SubtaskDTO dto) {
        return subtaskService.changeSubtaskName(dto);
    }
    @PutMapping("/change-checked")
    public ResponseEntity<ApiResponse> changeSubtaskChecked(@RequestBody SubtaskDTO dto) {
        return subtaskService.changeSubtaskCheck(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> changeSubtaskChecked(@PathVariable("id") Long id) {
        return subtaskService.deleteSubtask(id);
    }
}
