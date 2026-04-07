package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.SaveEvidenceDTO;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.UpdateEvidenceDTO;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.utils.EvidenceUtils;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.EvidenceFile;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.dtos.FileDetailsDTO;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskRepository;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EvidenceService {
    private final EvidenceRepository evidenceRepository;
    private final TaskRepository taskRepository;

    public EvidenceService(
            EvidenceRepository evidenceRepository,
            TaskRepository taskRepository
    ) {
        this.evidenceRepository = evidenceRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response;
        List<Evidence> evidences = evidenceRepository.findAll();
        if(evidences.isEmpty()){
            response = new ApiResponse(
                    "Evidencias no registradas.",
                    true,
                    HttpStatus.NOT_FOUND
            );
        } else {
            response = new ApiResponse(
                    "Evidencias encontradas con éxito.",
                    EvidenceUtils.entityListToSummaryDTO(evidences),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getEvidencesByAdvisorId(Long advisorId) {
        ApiResponse response;
        List<Evidence> evidences = evidenceRepository.getEvidencesByAdvisorId(advisorId);
        if(evidences.isEmpty()){
            response = new ApiResponse(
                    "Evidencias no registradas.",
                    true,
                    HttpStatus.NOT_FOUND
            );
        } else {
            response = new ApiResponse(
                    "Evidencias encontradas con éxito.",
                    EvidenceUtils.entityListToSummaryDTO(evidences),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> saveEvidence(SaveEvidenceDTO dto) {
        ApiResponse response;
        try {
            Evidence evidence = new Evidence();
            Task task = taskRepository.getReferenceById(dto.getIdTask());
            task.setStatus(TaskStatus.InRevision);
            evidence.setTask(task);
            evidence.setUploadDate(LocalDateTime.now());
            evidence.setWorkedHours(dto.getWorkedHours());
            evidence.setDescription(dto.getDescription());
            evidence.setStatus(EvidenceStatus.InRevision);

            for(FileDetailsDTO f : dto.getFiles()) {
                EvidenceFile file = new EvidenceFile();
                file.setFile(f.name());
                file.setType(f.type());
                file.setUrl(f.url());
                evidence.addFiles(file);
            }
            Evidence saved = evidenceRepository.save(evidence);

            response = new ApiResponse(
                    "Evidencia registrada con éxito.",
                    saved,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            response = new ApiResponse(
                    "Error interno del servidor.",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<ApiResponse> updateEvidence(UpdateEvidenceDTO dto) {
        ApiResponse response;
        try {
            Evidence found = evidenceRepository.findById(dto.id()).orElse(null);
            if(found != null){
                Task task = found.getTask();
                EvidenceStatus newStatus = dto.evidenceStatus();

                if(newStatus != null) {
                    if(newStatus.equals(EvidenceStatus.Approved)){
                        task.setStatus(TaskStatus.Completed);
                    } else if (newStatus.equals(EvidenceStatus.Rejected)) {
                        task.setStatus(TaskStatus.Rejected);
                    } else {
                        newStatus = EvidenceStatus.InRevision;
                        task.setStatus(TaskStatus.InRevision);
                    }
                    found.setStatus(newStatus);
                }
                if(dto.feedback() != null) {
                    found.setFeedback(dto.feedback());
                    found = evidenceRepository.saveAndFlush(found);
                }

                response = new ApiResponse(
                        "Evidencia actualizada con éxito",
                        EvidenceUtils.entityToSummaryDTO(found),
                        HttpStatus.NOT_FOUND
                );
            } else {
                response = new ApiResponse(
                        "Evidencia no encontrada",
                        true,
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            response = new ApiResponse(
                    "Error interno del servidor.",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

}
