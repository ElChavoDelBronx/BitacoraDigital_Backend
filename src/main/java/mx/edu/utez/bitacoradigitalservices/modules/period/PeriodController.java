package mx.edu.utez.bitacoradigitalservices.modules.period;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.SavePeriodDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/periods")
public class PeriodController {

    private final PeriodService service;

    public PeriodController(PeriodService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        Period period = service.findById(id);

        if (period == null) {
            return new ApiResponse(
                    "Periodo no encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ApiResponse(
                "Periodo encontrado",
                period,
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody SavePeriodDTO dto) {
        return service.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody SavePeriodDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}