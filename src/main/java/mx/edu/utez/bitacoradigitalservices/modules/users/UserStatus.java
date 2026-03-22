package mx.edu.utez.bitacoradigitalservices.modules.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatus {
    @JsonProperty("active")
    Active,
    @JsonProperty("inactive")
    Inactive
}
