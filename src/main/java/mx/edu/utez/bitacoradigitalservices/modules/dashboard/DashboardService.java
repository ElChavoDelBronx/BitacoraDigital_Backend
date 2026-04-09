package mx.edu.utez.bitacoradigitalservices.modules.dashboard;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.AdminDashboardDTO;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.AdminStatisticsDTO;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.StudentDashboardDTO;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.StudentStatisticsDTO;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ActiveProjectsAndStudents;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.RecentEvidences;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.StudentDashboardTaskCount;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.EvidenceRepository;
import mx.edu.utez.bitacoradigitalservices.modules.period.PeriodRepository;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskRepository;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.utils.TaskUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {
    private final TaskRepository taskRepository;
    private final PeriodRepository periodRepository;
    private final EvidenceRepository evidenceRepository;

    public DashboardService(TaskRepository taskRepository, PeriodRepository periodRepository, EvidenceRepository evidenceRepository) {
        this.taskRepository = taskRepository;
        this.periodRepository = periodRepository;
        this.evidenceRepository = evidenceRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getStudentDashboard(Long studentId) {
        ApiResponse response;

        StudentDashboardTaskCount taskCount = taskRepository.findStudentDashboardTaskCount(studentId);
        List<Task> recentTasks = taskRepository.findRecentTasksByStudent(studentId);
        Long validatedHours = taskRepository.countValidateHoursByStudent(studentId);
        String periodAlias = periodRepository.findActivePeriod(LocalDateTime.now()).getNamePeriod();

        response = new ApiResponse(
                "Información encontrada con éxito",
                new StudentDashboardDTO(
                        periodAlias,
                        new StudentStatisticsDTO(taskCount.getCompletedTask(), taskCount.getInProgressTask(), taskCount.getTotalTasks(), validatedHours),
                        TaskUtils.entityListToSimplifiedTaskSummaryDTO(recentTasks)
                ),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAdminDashboard() {
        ApiResponse response;

        ActiveProjectsAndStudents active = periodRepository.findActiveProjectsAndStudents();
        long completedTasks = taskRepository.countCompletedTasks();
        List<RecentEvidences> recentEvidences = evidenceRepository.getRecentEvidences();


        response = new ApiResponse(
                "Información encontrada con éxito",
                new AdminDashboardDTO(
                        new AdminStatisticsDTO(completedTasks, active.getActiveStudents(), active.getActiveProjects()),
                        recentEvidences, completedTasks
                ),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }
}
