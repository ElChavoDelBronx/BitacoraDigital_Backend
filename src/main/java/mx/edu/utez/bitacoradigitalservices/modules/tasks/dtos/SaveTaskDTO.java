package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDateTime;

public record SaveTaskDTO(
        Long id,
        String nameTask,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        Long projectId,
        Long studentId
) {
}
