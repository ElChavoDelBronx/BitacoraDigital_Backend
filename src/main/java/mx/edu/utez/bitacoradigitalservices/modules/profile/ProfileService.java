package mx.edu.utez.bitacoradigitalservices.modules.profile;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.projects.ProjectRepository;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public ProfileService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getStudentProfile(Long studentId) {
        ApiResponse response;
        User student = userRepository.findById(studentId).orElse(null);

        if(student == null) {
            response = new ApiResponse("Estudiante no encontrado", true, HttpStatus.BAD_REQUEST);
        } else {
            response = new ApiResponse(
                    "Estudiante encontrado",
                    projectRepository.getStudentProfile(studentId),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
}
