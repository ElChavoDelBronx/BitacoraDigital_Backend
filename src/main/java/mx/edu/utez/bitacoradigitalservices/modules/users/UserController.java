package mx.edu.utez.bitacoradigitalservices.modules.users;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserCreateDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ¡ESTA ES LA LÍNEA MÁGICA QUE SOLUCIONA EL ERROR!
@CrossOrigin(origins = {"http://localhost:5173"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/students/{projectId}")
    public ResponseEntity<ApiResponse> findStudentsByProject(@PathVariable("projectId") Long projectId) {
        return userService.findStudentsByProjectId(projectId);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserCreateDTO dto) {
        return userService.saveUser(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UserListDTO dto) {
        return userService.updateUser(id, dto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatusUser(@PathVariable Long id) {
        return userService.updateStatusUser(id);
    }

}