package mx.edu.utez.bitacoradigitalservices.modules.projects.dtos;

public record BasicProjectDTO(
        Long id, String name, String description,
        String advisorName, String periodName
) {
}
