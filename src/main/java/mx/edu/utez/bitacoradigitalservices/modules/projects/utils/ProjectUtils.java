package mx.edu.utez.bitacoradigitalservices.modules.projects.utils;

import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.ProjectSummaryDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

public class ProjectUtils {
    public static ProjectSummaryDTO entityToSummaryDTO(Project p) {
        User adviser = p.getAdviser();

        return new ProjectSummaryDTO(
                p.getId(),
                p.getNameProject(),
                p.getDescription(),
                String.format("%s %s", adviser.getNameUser(), adviser.getLastname()),
                p.getPeriod().getNamePeriod(),
                (long) p.getStudents().size()
        );
    }
}
