package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.BasicEvidenceDTO;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedTaskDTO(
        Long id,
        String title,
        String projectName,
        String description,
        TaskStatus status,
        LocalDateTime dueDate,
        List<SubTask> subTasks,
        List<BasicEvidenceDTO> evidences
) {
}
