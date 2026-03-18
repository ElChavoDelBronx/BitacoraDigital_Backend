package mx.edu.utez.bitacoradigitalservices.modules.users.dtos;

import jakarta.persistence.Column;

public record UserCreateDTO (
    String nameUser,
    String lastName,
    String email,
    String password,
    String rol
) {}
