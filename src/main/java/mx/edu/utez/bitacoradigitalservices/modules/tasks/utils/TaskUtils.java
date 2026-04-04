package mx.edu.utez.bitacoradigitalservices.modules.tasks.utils;

import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.BasicEvidenceDTO;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.utils.EvidenceFileUtils;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.DetailedTaskDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.TaskBoardDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.TaskSummaryDTO;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

import java.util.ArrayList;
import java.util.List;

public class TaskUtils {
    public static DetailedTaskDTO entityToDetailedTaskDTO(Task task) {
        List<BasicEvidenceDTO> evidences = task.getEvidences().stream()
                .map(e -> new BasicEvidenceDTO(
                        e.getFeedback(), e.getStatus(),
                        EvidenceFileUtils.entityListToListDTO(e.getFiles()))
                ).toList();
        System.out.println("Fecha original: "+task.getDueDate());
        return new DetailedTaskDTO(
                task.getId(),
                task.getNameTask(),
                task.getProject().getNameProject(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getSubTask(),
                evidences
        );
    }
    public static List<TaskSummaryDTO> entityListToSimplifiedTaskSummaryDTO(List<Task> tasks) {
        List<TaskSummaryDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskSummaryDTO dto = new TaskSummaryDTO(
                    task.getId(),
                    task.getNameTask(),
                    task.getProject().getNameProject(),
                    task.getStatus()
            );
            dtos.add(dto);
        }
        return dtos;
    }
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
    public static TaskBoardDTO entityToBoardDTO(Task task) {
        User student  = task.getStudent();
        return new TaskBoardDTO(
                task.getId(),
                task.getNameTask(),
                String.format("%s %s", student.getNameUser(), student.getLastName()),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getSubTask()
        );
    }
    public static List<TaskBoardDTO> entityListToBoardDTO(List<Task> tasks) {
        List<TaskBoardDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(entityToBoardDTO(task));
        }
        return dtos;
    }
}
