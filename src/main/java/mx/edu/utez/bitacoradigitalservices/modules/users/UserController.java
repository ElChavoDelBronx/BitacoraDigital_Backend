package mx.edu.utez.bitacoradigitalservices.modules.users;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAllUsers() { return userService.findAllUsers(); }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserCreateDTO dto) {
        return userService.saveUser(dto);
    }

}
