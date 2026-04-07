package mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TaskSummaryDTO {
        private Long id;
        private String title;
        private String projectName;
        private String description;
        private TaskStatus status;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDateTime dueDate;
        private List<SubTask> subTasks;

        public TaskSummaryDTO(Long id, String title, String projectName, String description, TaskStatus status, LocalDateTime dueDate, List<SubTask> subTasks) {
                this.id = id;
                this.title = title;
                this.projectName = projectName;
                this.description = description;
                this.status = status;
                this.dueDate = dueDate;
                this.subTasks = subTasks;
        }

        public TaskSummaryDTO(Long id, String title, String projectName, TaskStatus status) {
                this.id = id;
                this.title = title;
                this.projectName = projectName;
                this.status = status;
        }

        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }
        public void setTitle(String title) {
                this.title = title;
        }

        public String getProjectName() {
                return projectName;
        }
        public void setProjectName(String projectName) {
                this.projectName = projectName;
        }

        public String getDescription() {
                return description;
        }
        public void setDescription(String description) {
                this.description = description;
        }

        public TaskStatus getStatus() {
                return status;
        }
        public void setStatus(TaskStatus status) {
                this.status = status;
        }

        public LocalDateTime getDueDate() {
                return dueDate;
        }
        public void setDueDate(LocalDateTime dueDate) {
                this.dueDate = dueDate;
        }

        public List<SubTask> getSubTasks() {
                return subTasks;
        }
        public void setSubTasks(List<SubTask> subTasks) {
                this.subTasks = subTasks;
        }
}
