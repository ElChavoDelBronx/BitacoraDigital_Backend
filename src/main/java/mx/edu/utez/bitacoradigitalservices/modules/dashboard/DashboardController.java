package mx.edu.utez.bitacoradigitalservices.modules.dashboard;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse> getStudentDashboard(@PathVariable("studentId") Long studentId) {
        return dashboardService.getStudentDashboard(studentId);
    }
    @GetMapping("/admin")
    public ResponseEntity<ApiResponse> getAdminDashboard() {
        return dashboardService.getAdminDashboard();
    }

}
