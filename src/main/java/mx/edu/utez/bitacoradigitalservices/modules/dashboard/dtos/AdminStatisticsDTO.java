package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

public record AdminStatisticsDTO(
        Long completedTasks,
        Long activeStudents,
        Long activeProjects
) {
}
