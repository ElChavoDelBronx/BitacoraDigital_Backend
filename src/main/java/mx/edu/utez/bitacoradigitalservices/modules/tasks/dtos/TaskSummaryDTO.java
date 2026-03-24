package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public record TaskSummaryDTO(
        Long id,
        String title,
        String projectName,
        String description,
        TaskStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime dueDate,
        List<SubTask> subTasks
) {
}
