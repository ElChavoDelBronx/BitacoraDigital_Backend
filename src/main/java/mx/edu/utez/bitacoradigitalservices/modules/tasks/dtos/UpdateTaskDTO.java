package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDateTime;

public record UpdateTaskDTO(
        Long id,
        String title,
        Long studentId,
        String description,
        TaskStatus status,
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dueDate
) {
}
