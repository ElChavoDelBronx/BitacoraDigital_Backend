package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDate;

public record SaveTaskDTO(
        Long id,
        String nameTask,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        Long projectId,
        Long studentId
) {
}
