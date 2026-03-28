package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EvidenceStatus {
    @JsonProperty("in_revision")
    InRevision,
    @JsonProperty("rejected")
    Rejected,
    @JsonProperty("approved")
    Approved
}
