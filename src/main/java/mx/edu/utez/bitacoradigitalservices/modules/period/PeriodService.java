package mx.edu.utez.bitacoradigitalservices.modules.period;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.SavePeriodDTO;
import mx.edu.utez.bitacoradigitalservices.modules.period.utils.PeriodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodService {

    private final PeriodRepository repository;

    public PeriodService(PeriodRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse response = new ApiResponse(
                "Periodos obtenidos",
                PeriodUtils.entityListToSummaryDTOList(repository.findAll()),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, response.getStatus());
    }

    public Period findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> save(SavePeriodDTO dto) {
        ApiResponse response;
        try {
            Period period = new Period();
            period.setNamePeriod(dto.name());
            period.setStartDate(dto.startDate());
            period.setDueDate(dto.endDate());

            repository.save(period);

            response = new ApiResponse("Periodo registrado con éxito", HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> update(SavePeriodDTO dto) {
        ApiResponse response;
        try {
            Period existing = repository.findById(dto.id()).orElse(null);
            if (existing != null) {
                existing.setNamePeriod(dto.name());
                existing.setStartDate(dto.startDate());
                existing.setDueDate(dto.endDate());

                repository.save(existing);
                response = new ApiResponse("Periodo registrado con éxito", HttpStatus.CREATED);
            } else {
                response = new ApiResponse("Periodo no encontrado", true, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> delete(Long id) {
        ApiResponse response;
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                response = new ApiResponse("Periodo borrado con éxito", HttpStatus.NO_CONTENT);
            } else {
                response = new ApiResponse("Periodo no encontrado", true, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response = new ApiResponse("Error Interno del Servidor", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}