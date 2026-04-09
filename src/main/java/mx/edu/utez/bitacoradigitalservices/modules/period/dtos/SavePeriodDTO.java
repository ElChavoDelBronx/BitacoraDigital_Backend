package mx.edu.utez.bitacoradigitalservices.modules.period.dtos;

import java.time.LocalDateTime;

public record SavePeriodDTO(
        Long id, String name, LocalDateTime startDate, LocalDateTime endDate
) {
}
