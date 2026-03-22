package mx.edu.utez.bitacoradigitalservices.modules.users.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.users.UserStatus;

public record UserListDTO (
        Long id,
        String nameUser,
        String lastName,
        String email,
        String rol,
        UserStatus userStatus
){}
