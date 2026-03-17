package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.util.Date;
import java.util.List;

public record TaskSummaryDTO(
        String title,
        String description,
        TaskStatus status,
        Date dueDate,
        List<SubTask> subTasks
) {
}
