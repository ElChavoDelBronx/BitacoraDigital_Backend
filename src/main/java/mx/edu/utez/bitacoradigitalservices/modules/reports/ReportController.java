package mx.edu.utez.bitacoradigitalservices.modules.reports;

import mx.edu.utez.bitacoradigitalservices.modules.reports.dto.ProjectReportDto;
import mx.edu.utez.bitacoradigitalservices.modules.reports.dto.StudentReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getProjectReport(@PathVariable("id") Long projectId) {
        try {
            ProjectReportDto report = reportService.getProjectProgressReport(projectId);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentReport(@PathVariable("id") Long studentId) {
        try {
            StudentReportDto report = reportService.getStudentHoursReport(studentId);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}