package mx.edu.utez.bitacoradigitalservices.modules.auth;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthResponse;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.ChangePasswordRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.ResetPasswordRequest;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserStatus;
import mx.edu.utez.bitacoradigitalservices.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

            if(user.getUserStatus() != UserStatus.Active){
                throw new RuntimeException("Tu cuenta está inactiva. Contacta al administrador.");
            }

            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

                String token = UUID.randomUUID().toString();

                user.setToken(token);
                userRepository.save(user);
                return new AuthResponse(
                        token,
                        user.getRol(),
                        user.getId(),
                        String.format("%s %s", user.getNameUser(), user.getLastName()),
                        user.isFirstSignIn()
                );
            }
        }
        throw new RuntimeException("Credenciales no válidas");
    }

    public void requestPasswordReset(ResetPasswordRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String code = String.format("%06d", new java.util.Random().nextInt(999999));

            user.setResetToken(code);
            user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(15));

            userRepository.save(user);
            emailService.sendPasswordResetCode(user.getEmail(), code);
        }
    }

    private void verifyResetToken(User user) {
        if (user.getResetTokenExpiration() != null && LocalDateTime.now().isAfter(user.getResetTokenExpiration())) {
            user.setResetToken(null);
            user.setResetTokenExpiration(null);
            userRepository.save(user);
            throw new RuntimeException("El código ha expirado. Por favor, solicita uno nuevo.");
        }
    }

    public ApiResponse verifyReset(ChangePasswordRequest request) {
        Optional<User> userOptional = userRepository.findByEmailAndResetToken(request.getEmail(), request.getCode());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            verifyResetToken(user);
            return new ApiResponse(
                    "Código válido",
                    HttpStatus.OK
            );
        }
        throw new RuntimeException("El código es inválido o no pertenece a este correo");
    }

    public String changePassword(ChangePasswordRequest request) {
        Optional<User> userOptional = userRepository.findByEmailAndResetToken(request.getEmail(), request.getCode());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            verifyResetToken(user);

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setResetToken(null);
            user.setResetTokenExpiration(null);
            userRepository.save(user);

            return "Contraseña actualizada exitosamente";
        }
        throw new RuntimeException("El código es inválido o no pertenece a este correo");
    }

    public ApiResponse changeInitialPassword(String email, String tempPassword, String newPassword) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (!user.isFirstSignIn()) {
                throw new RuntimeException("El usuario ya ha cambiado su contraseña inicial anteriormente");
            }

            if (!passwordEncoder.matches(tempPassword, user.getPassword())) {
                throw new RuntimeException("La contraseña temporal es incorrecta");
            }

            user.setPassword(passwordEncoder.encode(newPassword));

            user.setFirstSignIn(false);

            userRepository.save(user);

            return new ApiResponse("Contraseña actualizada exitosamente", HttpStatus.OK);
        }
        throw new RuntimeException("Usuario no encontrado");
    }
}