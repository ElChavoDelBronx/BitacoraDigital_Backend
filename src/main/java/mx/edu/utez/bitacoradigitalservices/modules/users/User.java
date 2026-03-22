package mx.edu.utez.bitacoradigitalservices.modules.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "name_user", nullable = false)
    private String nameUser;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "rol", nullable = false)
    private String rol;
    @Column(name = "userStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Column(name = "token", length = 500)
    private String token;
    @Column(name = "reset_token", length = 500)
    private String resetToken;
    @Column(name = "reset_token_expiration")
    private LocalDateTime resetTokenExpiration;

    @OneToMany(mappedBy = "adviser")
    @JsonIgnore
    private List<Project> advisers;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<Project> projects;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Task> tasks;


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getNameUser() { return nameUser; }
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }
    public void setToken(String token) { this.token = token; }
    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }

    public LocalDateTime getResetTokenExpiration() { return resetTokenExpiration; }
    public void setResetTokenExpiration(LocalDateTime resetTokenExpiration) { this.resetTokenExpiration = resetTokenExpiration; }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
    @OneToMany(mappedBy = "students")
    private List<Task> assignedTasks
     */
}
