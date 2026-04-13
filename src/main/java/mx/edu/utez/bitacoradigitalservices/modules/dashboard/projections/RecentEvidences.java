package mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections;

import java.time.LocalDateTime;

public interface RecentEvidences {
    Long getId();
    String getStudentName();
    String getTaskTitle();
    LocalDateTime getUploadDate();
}
