package mx.edu.utez.bitacoradigitalservices.modules.users.utils;

import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserStatus;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.UserListDTO;

public class UserUtils {
    public static UserListDTO entityToSummaryDTO(User user) {
        String nameProject = "Sin proyecto";

        if (user.getProjects() != null && !user.getProjects().isEmpty()) {
            nameProject = user.getProjects().get(0).getNameProject();
        }

        UserStatus userStatus = UserStatus.Active;

        return new UserListDTO(
                user.getId(),
                user.getNameUser(),
                user.getLastName(),
                user.getEmail(),
                user.getRol(),
                userStatus
        );
    }
}
