package mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.utils;

import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.EvidenceFile;
import mx.edu.utez.bitacoradigitalservices.modules.evidenceFiles.dtos.FileDetailsDTO;

import java.util.ArrayList;
import java.util.List;

public class EvidenceFileUtils {
    public static FileDetailsDTO entityToDTO(EvidenceFile file) {
        return new FileDetailsDTO(
                file.getFile(),
                file.getUrl(),
                file.getType()
        );
    }
    public static List<FileDetailsDTO> entityListToListDTO(List<EvidenceFile> files) {
        List<FileDetailsDTO> dtoList = new ArrayList<>();
        for (EvidenceFile file : files) {
            dtoList.add(entityToDTO(file));
        }
        return dtoList;
    }
}
