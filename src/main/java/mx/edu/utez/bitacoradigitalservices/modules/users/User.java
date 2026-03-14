package mx.edu.utez.bitacoradigitalservices.modules.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;

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
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "rol", nullable = false)
    private String rol;
    @Column(name = "token", length = 500)
    private String token;

    @OneToMany(mappedBy = "adviser")
    @JsonIgnore
    private List<Project> advisers;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<Project> students;


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getNameUser() { return nameUser; }
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }



    /*
    @OneToMany(mappedBy = "students")
    private List<Task> assignedTasks
     */
}
