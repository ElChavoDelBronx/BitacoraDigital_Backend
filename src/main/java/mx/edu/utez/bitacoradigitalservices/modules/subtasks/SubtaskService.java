package mx.edu.utez.bitacoradigitalservices.modules.subtasks;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.subtasks.dtos.SubtaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubtaskService {
    private final SubtaskRepository subtaskRepository;

    public SubtaskService(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> changeSubtaskName(SubtaskDTO dto) {
        ApiResponse response;

        try {
            SubTask found = subtaskRepository.findById(dto.id()).orElse(null);

            if(found != null) {
                found.setName(dto.name());
                if(dto.name() != null && !dto.name().isEmpty()) {
                    subtaskRepository.save(found);
                    response = new ApiResponse("Subtarea modificada con éxito", HttpStatus.OK);
                } else {
                    response = new ApiResponse("Descripción vacía o nula", true, HttpStatus.BAD_REQUEST);
                }
            } else {
                response = new ApiResponse("Subtarea no encontrada", true, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> changeSubtaskCheck(SubtaskDTO dto) {
        ApiResponse response;

        try {
            SubTask found = subtaskRepository.findById(dto.id()).orElse(null);

            if(found != null) {
                subtaskRepository.changeSubtaskChecked(found.getId());
                response = new ApiResponse("Subtarea modificada con éxito", HttpStatus.OK);
            } else {
                response = new ApiResponse("Subtarea no encontrada", true, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> deleteSubtask(Long id) {
        ApiResponse response;

        try {
            SubTask found = subtaskRepository.findById(id).orElse(null);

            if(found != null) {
                subtaskRepository.deleteById(found.getId());
                response = new ApiResponse("Subtarea eliminada con éxito", HttpStatus.NO_CONTENT);
            } else {
                response = new ApiResponse("Subtarea no encontrada", true, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

}
