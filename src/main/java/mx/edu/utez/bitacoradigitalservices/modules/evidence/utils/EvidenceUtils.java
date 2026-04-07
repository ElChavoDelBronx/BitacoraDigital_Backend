package mx.edu.utez.bitacoradigitalservices.modules.evidence.utils;

import mx.edu.utez.bitacoradigitalservices.modules.evidence.Evidence;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.EvidenceSummaryDTO;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.dtos.FileDetailsDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;

import java.util.ArrayList;
import java.util.List;

public class EvidenceUtils {
    public static EvidenceSummaryDTO entityToSummaryDTO(Evidence evidence) {
        Task task = evidence.getTask();
        User student = task.getStudent();
        List<FileDetailsDTO> fileDtos = evidence.getFiles().stream()
                .map(f -> new FileDetailsDTO(f.getFile(), f.getUrl(), f.getType()))
                .toList();

        return new EvidenceSummaryDTO(
                evidence.getId(),
                evidence.getUploadDate(),
                evidence.getDescription(),
                task.getNameTask(),
                task.getProject().getNameProject(),
                String.format("%s %s", student.getNameUser(), student.getLastName()),
                evidence.getStatus(),
                evidence.getFeedback(),
                fileDtos
        );
    }
    public static List<EvidenceSummaryDTO> entityListToSummaryDTO(List<Evidence> evidences) {
        List<EvidenceSummaryDTO> dtos = new ArrayList<>();
        for (Evidence evidence : evidences) {
            dtos.add(entityToSummaryDTO(evidence));
        }
        return dtos;
    }
}
