package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.SaveEvidenceDTO;
import mx.edu.utez.bitacoradigitalservices.modules.evidence.dtos.UpdateEvidenceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evidences")
public class EvidenceController {
    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllEvidences() {
        return evidenceService.getAll();
    }
    @GetMapping("{advisorId}")
    public ResponseEntity<ApiResponse> getEvidencesByAdvisorId(@PathVariable("advisorId") Long advisorId) {
        return evidenceService.getEvidencesByAdvisorId(advisorId);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveEvidence(@RequestBody SaveEvidenceDTO dto) {
        return evidenceService.saveEvidence(dto);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> updateEvidence(@RequestBody UpdateEvidenceDTO dto) {
        return evidenceService.updateEvidence(dto);
    };
}
