package mx.edu.utez.bitacoradigitalservices.modules.projects.utils;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodDTO;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.BasicProjectDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.BasicUserDTO;
import java.util.List;

public class ProjectUtils {
    public static BasicProjectDTO entityToBasicDTO(Project p) {
        User adviser = p.getAdviser();
        Period period = p.getPeriod();
        List<BasicUserDTO> students = p.getStudents().stream()
                .map(s -> new BasicUserDTO(s.getId(), String.format("%s %s", s.getNameUser(), s.getLastName())))
                .toList();

        return new BasicProjectDTO(
                p.getId(),
                p.getNameProject(),
                p.getDescription(),
                p.getNeededHours(),
                new BasicPeriodDTO(period.getId(), period.getNamePeriod()),
                new BasicUserDTO(adviser.getId(), String.format("%s %s", adviser.getNameUser(), adviser.getLastName())),
                students
        );
    }
}
