package mx.edu.utez.bitacoradigitalservices.modules.auth;

import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthResponse;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.ChangePasswordRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.ResetPasswordRequest;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import mx.edu.utez.bitacoradigitalservices.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public AuthResponse login(AuthRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

                String token = UUID.randomUUID().toString();

                user.setToken(token);
                userRepository.save(user);
                return new AuthResponse(token, user.getRol());
            }
        }
        throw new RuntimeException("Credenciales no válidas");
    }

    public void requestPasswordReset(ResetPasswordRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            userRepository.save(user);

            emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
        }
    }

    public String changePassword(ChangePasswordRequest request) {
        Optional<User> userOptional = userRepository.findByResetToken(request.getToken());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setResetToken(null);
            userRepository.save(user);

            return "Contraseña actualizada exitosamente";
        }
        throw new RuntimeException("Token de reinicio no válido");
    }
}