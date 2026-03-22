package mx.edu.utez.bitacoradigitalservices.modules.users;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAllUsers () {
        ApiResponse response = new ApiResponse(
                "Usuarios Obtenidos Correctamente.",
                userRepository.findAllUserList(),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> saveUser (UserCreateDTO dto) {
        ApiResponse response;
        User user = new User();
        user.setNameUser(dto.nameUser());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setRol(dto.rol());
        user.setUserStatus(UserStatus.Active);
        user.setPassword("admin");
        try {
            User saved = userRepository.save(user);
            response = new ApiResponse(
                    "Usuarios Obtenidos Correctamente.",
                    saved,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            response = new ApiResponse(
                    "Error al crear usuario",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}
