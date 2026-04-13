package mx.edu.utez.bitacoradigitalservices.modules.profile.dtos;

public record StudentProfileDTO(
    String email, String activePeriod, Integer neededHours, Long validatedHours
) {
}
