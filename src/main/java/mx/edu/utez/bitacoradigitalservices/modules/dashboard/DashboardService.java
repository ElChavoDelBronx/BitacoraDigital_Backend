package mx.edu.utez.bitacoradigitalservices.modules.dashboard;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.*;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ActiveProjectsAndStudents;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ProjectProgressProjection;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.RecentEvidences;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.StudentDashboardTaskCount;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.EvidenceRepository;
import mx.edu.utez.bitacoradigitalservices.modules.period.PeriodRepository;
import mx.edu.utez.bitacoradigitalservices.modules.projects.ProjectRepository;
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
    private final ProjectRepository projectRepository;

    public DashboardService(TaskRepository taskRepository, PeriodRepository periodRepository, EvidenceRepository evidenceRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.periodRepository = periodRepository;
        this.evidenceRepository = evidenceRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getStudentDashboard(Long studentId) {
        ApiResponse response;

        StudentDashboardTaskCount taskCount = taskRepository.findStudentDashboardTaskCount(studentId);
        List<Task> recentTasks = taskRepository.findRecentTasksByStudent(studentId);
        Long validatedHours = taskRepository.countValidateHoursByStudent(studentId);
        String periodAlias = periodRepository.findActiveOrFuturePeriod(LocalDateTime.now()).getFirst().getPeriodAlias();

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
        List<ProjectProgressProjection> advance = projectRepository.findTop4ProjectProgress();


        response = new ApiResponse(
                "Información encontrada con éxito",
                new AdminDashboardDTO(
                        new AdminStatisticsDTO(completedTasks, active.getActiveStudents(), active.getActiveProjects()),
                        recentEvidences,
                        completedTasks,
                        advance
                ),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAdvisorDashboard(Long adviserId) {
        long totalTasks = taskRepository.countTotalTasksByAdviser(adviserId);
        long inProgress = taskRepository.countInProgressTasksByAdviser(adviserId);
        long validatedHours = evidenceRepository.sumValidatedHoursByAdviser(adviserId);

        List<RecentEvidences> recent = evidenceRepository.getRecentEvidencesByAdviser(adviserId);
        List<ProjectProgressProjection> advance = projectRepository.findTop4ProjectProgressByAdviser(adviserId);

        AdvisorDashboardDTO dto = new AdvisorDashboardDTO(
                new AdvisorStatisticsDTO(totalTasks, inProgress, validatedHours),
                recent,
                advance
        );

        ApiResponse response = new ApiResponse("Dashboard de asesor obtenido", dto, HttpStatus.OK);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
