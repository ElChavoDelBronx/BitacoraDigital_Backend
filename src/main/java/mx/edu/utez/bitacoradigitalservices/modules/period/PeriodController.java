package mx.edu.utez.bitacoradigitalservices.modules.period;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/periods")
public class PeriodController {

    private final PeriodService service;

    public PeriodController(PeriodService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse getAll() {
        return new ApiResponse(
                "Lista de periodos",
                service.findAll(),
                HttpStatus.OK
        );
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

    @PostMapping
    public ApiResponse save(@RequestBody Period period) {
        return new ApiResponse(
                "Periodo creado",
                service.save(period),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody Period period) {

        Period updated = service.update(id, period);

        if (updated == null) {
            return new ApiResponse(
                    "Periodo no encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ApiResponse(
                "Periodo actualizado",
                updated,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {

        boolean deleted = service.delete(id);

        if (!deleted) {
            return new ApiResponse(
                    "Periodo no encontrado",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }

        return new ApiResponse(
                "Periodo eliminado",
                HttpStatus.OK
        );
    }
}