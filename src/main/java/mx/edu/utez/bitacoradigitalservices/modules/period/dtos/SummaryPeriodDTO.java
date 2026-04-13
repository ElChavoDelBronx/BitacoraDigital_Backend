package mx.edu.utez.bitacoradigitalservices.modules.period.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;

import java.time.LocalDate;

public class SummaryPeriodDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public SummaryPeriodDTO(Period period) {
        this.id = period.getId();
        this.name = period.getNamePeriod();
        this.startDate = period.getStartDate();
        this.endDate = period.getDueDate();
        this.status = calculateStatus(period.getStartDate(), period.getDueDate());
    }

    private String calculateStatus(LocalDate start, LocalDate end) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(start)) {
            return "Futuro";
        }
        if (currentDate.isAfter(end)) {
            return "Inactivo";
        }
        return "Activo";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
