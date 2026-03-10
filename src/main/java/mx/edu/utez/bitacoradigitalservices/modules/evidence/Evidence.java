package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.EvidenceFile;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;

import java.time.LocalDateTime;
import java.util.Date;
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

    @OneToMany(mappedBy = "evidence")
    private List<EvidenceFile> files;
}
