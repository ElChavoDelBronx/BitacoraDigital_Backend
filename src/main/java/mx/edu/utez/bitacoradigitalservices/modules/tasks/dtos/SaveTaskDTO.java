package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import java.util.Date;

public record SaveTaskDTO(
        Long id,
        String nameTask,
        String description,
        Date dueDate,
        String status,
        Long projectId,
        Long studentId
) {
}
