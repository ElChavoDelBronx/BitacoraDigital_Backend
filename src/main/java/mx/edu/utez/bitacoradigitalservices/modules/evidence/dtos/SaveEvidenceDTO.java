package mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.dtos.FileDetailsDTO;

import java.util.ArrayList;
import java.util.List;

public class SaveEvidenceDTO {
    private Long idTask;
    private Integer workedHours;
    private String description = "";
    private List<FileDetailsDTO> files = new ArrayList<>();

    public SaveEvidenceDTO() {
    }

    public SaveEvidenceDTO(Long idTask, Integer workedHours, String description) {
        this.idTask = idTask;
        this.workedHours = workedHours;
        this.description = description;
    }

    public SaveEvidenceDTO(Long idTask, Integer workedHours, List<FileDetailsDTO> files) {
        this.idTask = idTask;
        this.workedHours = workedHours;
        this.files = files;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FileDetailsDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDetailsDTO> files) {
        this.files = files;
    }
}
