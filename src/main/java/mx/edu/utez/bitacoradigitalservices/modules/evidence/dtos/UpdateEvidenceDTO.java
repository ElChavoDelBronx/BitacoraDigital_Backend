package mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.evidence.EvidenceStatus;

public record UpdateEvidenceDTO(
        Long id,
        EvidenceStatus evidenceStatus,
        String feedback
) {
}
