package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

public record StudentStatisticsDTO(
        Long completedTasks,
        Long inProgressTasks,
        Long totalTasks,
        Long validatedHours
) {
}
