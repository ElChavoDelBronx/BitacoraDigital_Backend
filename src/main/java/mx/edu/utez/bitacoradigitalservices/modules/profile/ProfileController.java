package mx.edu.utez.bitacoradigitalservices.modules.profile;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse> getStudentProfile(@PathVariable("studentId") Long studentId) {
        return profileService.getStudentProfile(studentId);
    }
}
