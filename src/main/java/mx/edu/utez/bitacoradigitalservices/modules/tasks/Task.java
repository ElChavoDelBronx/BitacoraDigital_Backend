package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.Evidence;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "task")
public class Task extends BaseEntity {
    @Column(name = "name_task", nullable = false)
    private String nameTask;
    @Column(name = "description")
    private String description;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private User student;


    @OneToMany(mappedBy = "task")
    @JsonIgnore
    private List<SubTask> subTask;

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    private List<Evidence> evidences;

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public List<SubTask> getSubTask() {
        return subTask;
    }

    public void setSubTask(List<SubTask> subTask) {
        this.subTask = subTask;
    }

    public List<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<Evidence> evidences) {
        this.evidences = evidences;
    }
}

