package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.Evidence;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.SubTask;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task extends BaseEntity {
    @Column(name = "name_task", nullable = false)
    private String nameProyect;
    @Column(name = "description")
    private String description;
    @Column(name = "due_date", nullable = false)
    private Date dueDate;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private User student;


    @OneToMany(mappedBy = "task")
    private List<SubTask> subTask;

    @OneToMany(mappedBy = "task")
    private List<Evidence> evidences;

}
