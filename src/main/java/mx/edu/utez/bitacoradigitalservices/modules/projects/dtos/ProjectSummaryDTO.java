package mx.edu.utez.bitacoradigitalservices.modules.projects.dtos;

public interface ProjectSummaryDTO{
        Long getId();
        String getName();
        String getDescription();
        String getAdvisorName();
        String getPeriodName();
        Long getStudentCount();
        Long getTotalTasks();
        Long getCompletedTasks();
        Long getWorkedHours();
}
