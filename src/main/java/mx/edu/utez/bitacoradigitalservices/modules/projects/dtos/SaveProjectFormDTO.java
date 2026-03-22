package mx.edu.utez.bitacoradigitalservices.modules.projects.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodProjection;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.BasicUserProjection;

import java.util.List;

public record SaveProjectFormDTO(
        List<BasicPeriodProjection> periods,
        List<BasicUserProjection> students,
        List<BasicUserProjection> advisors
) {
}
