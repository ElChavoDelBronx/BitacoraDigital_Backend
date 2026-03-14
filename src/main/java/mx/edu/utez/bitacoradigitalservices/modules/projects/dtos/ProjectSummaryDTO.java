package mx.edu.utez.bitacoradigitalservices.modules.projects.dtos;

public record ProjectSummaryDTO(
        Long id,
        String projectName,
        String description,
        String advisorName,
        String periodName,
        Long studentCount
) {
}
