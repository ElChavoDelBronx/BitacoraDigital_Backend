package mx.edu.utez.bitacoradigitalservices.modules.period.projections;

import java.time.LocalDate;

public interface PeriodLimitsProjection {
    LocalDate getLowerLimit();
    LocalDate getUpperLimit();
}
