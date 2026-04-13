package mx.edu.utez.bitacoradigitalservices.modules.period;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "period")
public class Period extends BaseEntity {
    @Column(name = "name_period", nullable = false)
    private String namePeriod = "N/A";
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
