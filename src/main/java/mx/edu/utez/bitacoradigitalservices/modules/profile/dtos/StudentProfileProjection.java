package mx.edu.utez.bitacoradigitalservices.modules.profile.dtos;

public interface StudentProfileProjection {
        String getEmail();
        String getActivePeriod();
        Integer getNeededHours();
        Long getValidatedHours();
}
