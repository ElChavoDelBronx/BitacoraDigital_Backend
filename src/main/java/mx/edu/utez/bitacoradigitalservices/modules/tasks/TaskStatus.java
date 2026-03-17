package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskStatus {
    @JsonProperty("pending")
    Pending,
    @JsonProperty("in_progress")
    InProgress,
    @JsonProperty("in_revision")
    InRevision,
    @JsonProperty("completed")
    Completed,
    @JsonProperty("rejected")
    Rejected

}
