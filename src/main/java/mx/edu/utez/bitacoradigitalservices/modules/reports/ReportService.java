package mx.edu.utez.bitacoradigitalservices.modules.reports;

import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.projects.ProjectRepository;
import mx.edu.utez.bitacoradigitalservices.modules.reports.dto.ProjectReportDto;
import mx.edu.utez.bitacoradigitalservices.modules.reports.dto.StudentReportDto;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskRepository;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectReportDto getProjectProgressReport(Long projectId) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            throw new RuntimeException("Project not found");
        }

        long totalTasks = taskRepository.countTotalTasksByProjectId(projectId);
        long completedTasks = taskRepository.countCompletedTasksByProjectId(projectId);

        double percentage = (totalTasks == 0) ? 0.0 : ((double) completedTasks / totalTasks) * 100;

        percentage = Math.round(percentage * 100.0) / 100.0;

        return new ProjectReportDto(projectOpt.get().getNameProject(), totalTasks, completedTasks, percentage);
    }

    public StudentReportDto getStudentHoursReport(Long studentId) {
        Optional<User> studentOpt = userRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        User student = studentOpt.get();

        double loggedHours = taskRepository.sumLoggedHoursByStudentId(studentId);

        // Puedes cambiar esto si lo guardas en la base de datos
        double requiredHours = 480.0;

        double percentage = (loggedHours / requiredHours) * 100;
        percentage = Math.round(percentage * 100.0) / 100.0;

        String fullName = student.getNameUser() + " " + student.getLastname();

        return new StudentReportDto(fullName, loggedHours, requiredHours, Math.min(percentage, 100.0));
    }
}