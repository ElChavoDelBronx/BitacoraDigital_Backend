package mx.edu.utez.bitacoradigitalservices.modules.users;

import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import org.springframework.aop.Advisor;

import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "name_user", nullable = false)
    private String nameUser;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "rol", nullable = false)
    private String rol;

    @OneToMany(mappedBy = "adviser")
    private List<Project> advisers;

    @ManyToMany(mappedBy = "students")
    private List<Project> students;

    /*
    @OneToMany(mappedBy = "students")
    private List<Task> assignedTasks
     */
}
