package mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections;

import java.time.LocalDateTime;

public interface RecentEvidences {
    String getStudentName();
    String getTaskTitle();
    LocalDateTime getUploadDate();
}
