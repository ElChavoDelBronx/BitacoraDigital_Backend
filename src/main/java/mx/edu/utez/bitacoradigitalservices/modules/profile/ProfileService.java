package mx.edu.utez.bitacoradigitalservices.modules.profile;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.period.PeriodRepository;
import mx.edu.utez.bitacoradigitalservices.modules.profile.dtos.StudentProfileDTO;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.projects.ProjectRepository;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskRepository;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final PeriodRepository periodRepository;


    public ProfileService(
            UserRepository userRepository, ProjectRepository projectRepository,
            TaskRepository taskRepository, PeriodRepository periodRepository
    ) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.periodRepository = periodRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getStudentProfile(Long studentId) {
        ApiResponse response;
        User student = userRepository.findById(studentId).orElse(null);

        if(student == null) {
            response = new ApiResponse("Estudiante no encontrado", true, HttpStatus.BAD_REQUEST);
        } else {

            Integer neededHours = 0;
            Long validatedHours = 0L;
            Project activeProject = null;

            Period activePeriod = periodRepository.findActivePeriod(LocalDate.now()).orElse(new Period());
            if(activePeriod.getId() != null) {
                activeProject = projectRepository.findActiveProjectByStudentId(activePeriod.getId(), studentId);
            }
            if(activeProject != null) {
                neededHours = activeProject.getNeededHours();
                validatedHours = taskRepository.countValidateHoursByStudent(studentId);
            }

            response = new ApiResponse(
                    "Estudiante encontrado",
                    new StudentProfileDTO(student.getEmail(), activePeriod.getNamePeriod(), neededHours, validatedHours),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
}
