package mx.edu.utez.bitacoradigitalservices.modules.auth;

import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthResponse;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.ChangePasswordRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            AuthResponse response = authService.login(authRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/request-reset")
    public ResponseEntity<?> requestReset(@RequestBody ResetPasswordRequest request) {
        try {
            authService.requestPasswordReset(request);

            return ResponseEntity.ok("Si el correo electrónico existe en nuestro sistema, se ha enviado un enlace de recuperación.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            String result = authService.changePassword(request);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


}