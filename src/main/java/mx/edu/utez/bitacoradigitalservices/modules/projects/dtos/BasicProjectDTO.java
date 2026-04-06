package mx.edu.utez.bitacoradigitalservices.modules.projects.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.BasicUserDTO;

import java.util.List;

public record BasicProjectDTO(
        Long id, String name, String description,
        Integer neededHours, BasicPeriodDTO period,
        BasicUserDTO advisor, List<BasicUserDTO> students
) {
}
