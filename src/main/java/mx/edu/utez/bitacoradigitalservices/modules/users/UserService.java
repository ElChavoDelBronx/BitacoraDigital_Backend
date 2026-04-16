package mx.edu.utez.bitacoradigitalservices.modules.users;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.BasicUserProjection;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserCreateDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findStudentsByProjectId(Long projectId){
        ApiResponse response;
        List<BasicUserProjection> students = userRepository.findStudentsByProjectId(projectId);

        if(!students.isEmpty()){
            response = new ApiResponse(
                    "Lista de estudiantes encontrada.",
                    students,
                    HttpStatus.OK
            );
        } else {
            response = new ApiResponse(
                    "Estudiantes no encontrados.",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }
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
        user.setFirstSignIn(true);
        user.setPassword("admin");
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> updateUser(Long id, UserListDTO dto) {
        ApiResponse response;

        User usuarioExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuarioExist.setNameUser(dto.nameUser());
        usuarioExist.setLastName(dto.lastName());
        usuarioExist.setEmail(dto.email());
        usuarioExist.setRol(dto.rol());

        try {
            User updatedUser = userRepository.save(usuarioExist);

            response = new ApiResponse(
                    "Usuario actualizado correctamente.",
                    updatedUser,
                    HttpStatus.OK
            );

        } catch (Exception e) {
            response = new ApiResponse(
                    "Error al actualizar el usuario: " + e.getMessage(),
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> updateStatusUser(Long id) {
        ApiResponse response;
        try {
            User usuarioExist = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

            if (usuarioExist.getUserStatus() == UserStatus.Active) {
                usuarioExist.setUserStatus(UserStatus.Inactive);
            } else {
                usuarioExist.setUserStatus(UserStatus.Active);
            }

            User updatedUser = userRepository.save(usuarioExist);


            response = new ApiResponse(
                    "Estatus actualizado correctamente a: " + updatedUser.getUserStatus(),
                    updatedUser,
                    HttpStatus.OK
            );

        } catch (Exception e) {
            response = new ApiResponse(
                    "El estatus del usuario no pudo actualizarse: " + e.getMessage(),
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
}
