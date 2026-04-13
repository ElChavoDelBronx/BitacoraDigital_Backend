package mx.edu.utez.bitacoradigitalservices.modules.dashboard;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboards")
@CrossOrigin(origins = {"http://localhost:5173"})
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
    @PreAuthorize("hasAuthority('ROLE_Administrador')")
    public ResponseEntity<ApiResponse> getAdminDashboard() {
        System.out.println("🚨 ROLES DEL USUARIO ACTUAL: " +
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return dashboardService.getAdminDashboard();
    }

    @GetMapping("/advisor/{advisorId}")
    @PreAuthorize("hasAuthority('ROLE_Asesor')")
    public ResponseEntity<ApiResponse> getAdvisorDashboard(@PathVariable("advisorId") Long advisorId) {
        System.out.println("🚨 ROLES DEL USUARIO ACTUAL: " +
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return dashboardService.getAdvisorDashboard(advisorId);
    }
}
