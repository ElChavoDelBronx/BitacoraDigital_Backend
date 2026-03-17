package mx.edu.utez.bitacoradigitalservices.modules.tasks.utils;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.TaskSummaryDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskUtils {
    public static List<TaskSummaryDTO> entityListToSummaryDTO(List<Task> tasks) {
        List<TaskSummaryDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskSummaryDTO dto = new TaskSummaryDTO(
                    task.getNameTask(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getDueDate(),
                    task.getSubTask()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
