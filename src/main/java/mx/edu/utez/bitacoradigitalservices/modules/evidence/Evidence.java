package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.EvidenceFile;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evidence")
public class Evidence extends BaseEntity {
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;

    @ManyToOne
    @JoinColumn(name = "id_task", referencedColumnName = "id")
    private Task task;

    @Column(name = "worked_hours", nullable = false)
    private Integer workedHours;

    @OneToMany(mappedBy = "evidence")
    @JsonIgnore
    private List<EvidenceFile> files;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    public List<EvidenceFile> getFiles() {
        return files;
    }

    public void setFiles(List<EvidenceFile> files) {
        this.files = files;
    }
}
