package mx.edu.utez.bitacoradigitalservices.modules.projects;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.SaveProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAllProjects() {
        return projectService.findAllProjects();
    }
    @GetMapping("/advisor/{advisorId}")
    public ResponseEntity<ApiResponse> findAllProjects(@PathVariable("advisorId") Long advisorId) {
        return projectService.findProjectsByAdvisor(advisorId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findProjectById(@PathVariable("id") Long id) {
        return projectService.findProjectById(id);
    }
    @GetMapping("/saveFormData")
    public ResponseEntity<ApiResponse> findSaveFormData() {
        return projectService.findSavingFormData();
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> saveProject(@RequestBody SaveProjectDTO dto) {
        return projectService.saveProject(dto);
    }
    @PutMapping("")
    public ResponseEntity<ApiResponse> updateProject(@RequestBody SaveProjectDTO dto) {
        return projectService.updateProject(dto);
    }
}
