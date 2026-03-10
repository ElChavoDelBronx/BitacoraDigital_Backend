package mx.edu.utez.bitacoradigitalservices.modules.period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.projects.Project;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
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
    private List<Project> projects;
}
