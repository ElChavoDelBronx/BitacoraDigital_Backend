package mx.edu.utez.bitacoradigitalservices.modules.tasks.dto;

public class TaskUpdateDto {
    private String status;
    private Double loggedHours;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getLoggedHours() { return loggedHours; }
    public void setLoggedHours(Double loggedHours) { this.loggedHours = loggedHours; }
}