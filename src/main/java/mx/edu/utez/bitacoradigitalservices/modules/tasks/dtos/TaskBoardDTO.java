package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public record TaskBoardDTO(
        Long id,
        String title,
        String studentName,

        String description,
        TaskStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dueDate,
        List<SubTask> subTasks
) {
}
