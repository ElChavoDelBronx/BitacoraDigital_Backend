package mx.edu.utez.bitacoradigitalservices.modules.period.dtos;

import java.time.LocalDate;

public record SavePeriodDTO(
        Long id, String name, LocalDate startDate, LocalDate endDate
) {
}
