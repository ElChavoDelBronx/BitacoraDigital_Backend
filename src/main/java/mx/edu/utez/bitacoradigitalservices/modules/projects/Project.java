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

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAdviser() {
        return adviser;
    }

    public void setAdviser(User adviser) {
        this.adviser = adviser;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
