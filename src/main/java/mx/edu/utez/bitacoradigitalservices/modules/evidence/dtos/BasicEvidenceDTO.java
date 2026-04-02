package mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.evidence.EvidenceStatus;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.dtos.FileDetailsDTO;

import java.util.List;

public record BasicEvidenceDTO(
        String feedback,
        EvidenceStatus status,
        List<FileDetailsDTO> files
) {
}
