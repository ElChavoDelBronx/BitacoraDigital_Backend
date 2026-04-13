package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

public record AdvisorStatisticsDTO (
    Long totalTasks,
    Long inProgressTasks,
    Long validatedHours
) {}
