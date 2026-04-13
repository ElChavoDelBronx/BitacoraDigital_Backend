package mx.edu.utez.bitacoradigitalservices.modules.dashboard;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.StudentDashboardDTO;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos.StudentStatisticsDTO;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.StudentDashboardTaskCount;
import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.period.PeriodRepository;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskRepository;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.utils.TaskUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {
    private final TaskRepository taskRepository;
    private final PeriodRepository periodRepository;

    public DashboardService(TaskRepository taskRepository, PeriodRepository periodRepository) {
        this.taskRepository = taskRepository;
        this.periodRepository = periodRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getStudentDashboard(Long studentId) {
        ApiResponse response;

        StudentDashboardTaskCount taskCount = taskRepository.findStudentDashboardTaskCount(studentId);
        List<Task> recentTasks = taskRepository.findRecentTasksByStudent(studentId);
        Long validatedHours = taskRepository.countValidateHoursByStudent(studentId);
        Period activePeriod = periodRepository.findActivePeriod(LocalDate.now()).orElse(new Period());

        response = new ApiResponse(
                "Información encontrada con éxito",
                new StudentDashboardDTO(
                        activePeriod.getNamePeriod(),
                        new StudentStatisticsDTO(taskCount.getCompletedTask(), taskCount.getInProgressTask(), taskCount.getTotalTasks(), validatedHours),
                        TaskUtils.entityListToSimplifiedTaskSummaryDTO(recentTasks)
                ),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, response.getStatus());
    }
}
