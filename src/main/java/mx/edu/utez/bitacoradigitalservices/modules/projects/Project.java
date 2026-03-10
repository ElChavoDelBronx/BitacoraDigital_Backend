package mx.edu.utez.bitacoradigitalservices.modules.projects;

import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

import java.util.List;

@Entity
@Table(name = "project")
public class Project extends BaseEntity {
    @Column(name = "name_project", nullable = false)
    private String nameProject;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_adviser", referencedColumnName = "id")
    private User adviser;

    @ManyToOne
    @JoinColumn(name = "id_period", referencedColumnName = "id")
    private Period period;

    @ManyToMany
    @JoinTable(
            name = "student_has_project",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_student")
    )

    private List<User> students;
}
