package mx.edu.utez.bitacoradigitalservices.modules.period.dtos;

import java.time.LocalDate;

public class SuggestedDatesDTO{
    private LocalDate lastEndDate;
    private LocalDate suggestedStart;

    public SuggestedDatesDTO(){}

    public LocalDate getSuggestedStart() {
        return suggestedStart;
    }
    public void setSuggestedStart(LocalDate suggestedStart) {
        this.suggestedStart = suggestedStart;
    }

    public LocalDate getLastEndDate() {
        return lastEndDate;
    }

    public void setLastEndDate(LocalDate lastEndDate) {
        this.lastEndDate = lastEndDate;
    }
}
