package mx.edu.utez.bitacoradigitalservices.modules.period.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;

import java.time.LocalDateTime;

public class SummaryPeriodDTO {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public SummaryPeriodDTO(Period period) {
        this.id = period.getId();
        this.name = period.getNamePeriod();
        this.startDate = period.getStartDate();
        this.endDate = period.getDueDate();
        this.status = calculateStatus(period.getStartDate(), period.getDueDate());
    }

    private String calculateStatus(LocalDateTime start, LocalDateTime end) {
        LocalDateTime currentDate = LocalDateTime.now();
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
