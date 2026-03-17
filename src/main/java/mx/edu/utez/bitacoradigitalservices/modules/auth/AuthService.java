package mx.edu.utez.bitacoradigitalservices.modules.auth;

import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthRequest;
import mx.edu.utez.bitacoradigitalservices.modules.auth.dto.AuthResponse;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
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

    public AuthResponse login(AuthRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

                String token = UUID.randomUUID().toString();

                user.setToken(token);
                userRepository.save(user);
                return new AuthResponse(token, user.getRol(), user.getId());
            }
        }
        throw new RuntimeException("Invalid credentials");
    }
}