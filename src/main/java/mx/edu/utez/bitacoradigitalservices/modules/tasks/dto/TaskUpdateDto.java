package mx.edu.utez.bitacoradigitalservices.modules.tasks.dto;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

public class TaskUpdateDto {
    private TaskStatus status;
    private Double loggedHours;

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public Double getLoggedHours() { return loggedHours; }
    public void setLoggedHours(Double loggedHours) { this.loggedHours = loggedHours; }
}