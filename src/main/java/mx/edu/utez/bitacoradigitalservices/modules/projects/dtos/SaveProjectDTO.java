package mx.edu.utez.bitacoradigitalservices.modules.projects.dtos;

import java.util.List;

public record SaveProjectDTO(
        Long id,
        String projectName,
        String description,
        Long idPeriod,
        Long idAdviser,
        List<Long> studentIds,
        Integer neededHours
) {
}
