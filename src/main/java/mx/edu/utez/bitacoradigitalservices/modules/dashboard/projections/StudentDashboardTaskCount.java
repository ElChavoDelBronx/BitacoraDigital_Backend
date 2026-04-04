package mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections;

public interface StudentDashboardTaskCount {
    Long getCompletedTask();
    Long getInProgressTask();
    Long getTotalTasks();
}
