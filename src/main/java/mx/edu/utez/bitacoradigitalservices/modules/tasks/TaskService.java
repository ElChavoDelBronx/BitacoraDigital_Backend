package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.dto.TaskUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public String updateTaskStatusAndHours(Long taskId, TaskUpdateDto request) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);

        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setStatus(request.getStatus());

            if ("Completada".equalsIgnoreCase(request.getStatus()) && request.getLoggedHours() != null) {
                task.setLoggedHours(request.getLoggedHours());
            }

            taskRepository.save(task);
            return "Tarea actualizada exitosamente";
        }
        throw new RuntimeException("Tarea no encontrada");
    }
}
