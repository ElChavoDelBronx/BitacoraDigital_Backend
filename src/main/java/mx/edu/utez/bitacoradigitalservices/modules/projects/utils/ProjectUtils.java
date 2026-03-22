package mx.edu.utez.bitacoradigitalservices.modules.projects.utils;

import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.BasicProjectDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

public class ProjectUtils {
    public static BasicProjectDTO entityToBasicDTO(Project p) {
        User adviser = p.getAdviser();

        return new BasicProjectDTO(
                p.getId(),
                p.getNameProject(),
                p.getDescription(),
                String.format("%s %s", adviser.getNameUser(), adviser.getLastName()),
                p.getPeriod().getNamePeriod()
        );
    }
}
