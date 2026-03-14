package mx.edu.utez.bitacoradigitalservices.modules.period;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "period")
public class Period extends BaseEntity {
    @Column(name = "name_period", nullable = false)
    private String namePeriod;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;
    @Column(name = "state", nullable = false)
    private String state;

    @OneToMany(mappedBy = "period")
    @JsonIgnore
    private List<Project> projects;

    public Period() {}

    public String getNamePeriod() {
        return namePeriod;
    }

    public void setNamePeriod(String namePeriod) {
        this.namePeriod = namePeriod;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
