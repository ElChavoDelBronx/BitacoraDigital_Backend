package mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.evidence.EvidenceStatus;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.dtos.FileDetailsDTO;

import java.time.LocalDateTime;
import java.util.List;

public record EvidenceSummaryDTO(
    Long id,
    LocalDateTime uploadDate,
    String description,
    String taskName,
    String projectName,
    String studentName,
    EvidenceStatus status,
    String feedback,
    List<FileDetailsDTO> files
) {}
