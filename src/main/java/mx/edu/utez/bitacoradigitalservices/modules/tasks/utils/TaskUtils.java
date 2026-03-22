package mx.edu.utez.bitacoradigitalservices.modules.tasks.utils;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.TaskBoardDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.TaskSummaryDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

import java.util.ArrayList;
import java.util.List;

public class TaskUtils {
    public static List<TaskSummaryDTO> entityListToSummaryDTO(List<Task> tasks) {
        List<TaskSummaryDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskSummaryDTO dto = new TaskSummaryDTO(
                    task.getId(),
                    task.getNameTask(),
                    task.getProject().getNameProject(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getDueDate(),
                    task.getSubTask()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    public static List<TaskBoardDTO> entityListToBoardDTO(List<Task> tasks) {
        List<TaskBoardDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            User student = task.getStudent();
            TaskBoardDTO dto = new TaskBoardDTO(
                    task.getId(),
                    task.getNameTask(),
                    String.format("%s %s", student.getNameUser(), student.getLastName()),
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
