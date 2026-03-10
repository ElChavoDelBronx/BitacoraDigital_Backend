package mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles;

import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.Evidence;

@Entity
@Table(name = "evidence_file")
public class EvidenceFile extends BaseEntity {
    @Column(name = "file", nullable = false)
    private String file;

    @ManyToOne
    @JoinColumn(name = "id_evidence", referencedColumnName = "id")
    private Evidence evidence;
}
