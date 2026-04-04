package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.TaskSummaryDTO;

import java.util.List;

public class StudentDashboardDTO{
    private String activePeriod;
    private StudentStatisticsDTO stats;
    private List<TaskSummaryDTO> recentTasks;

    public StudentDashboardDTO(String activePeriod, StudentStatisticsDTO stats, List<TaskSummaryDTO> recentTasks) {
        this.activePeriod = activePeriod;
        this.stats = stats;
        this.recentTasks = recentTasks;
    }

    public String getActivePeriod() {
        return activePeriod;
    }
    public void setActivePeriod(String activePeriod) {
        this.activePeriod = activePeriod;
    }

    public StudentStatisticsDTO getStats() {
        return stats;
    }

    public void setStats(StudentStatisticsDTO stats) {
        this.stats = stats;
    }

    public List<TaskSummaryDTO> getRecentTasks() {
        return recentTasks;
    }

    public void setRecentTasks(List<TaskSummaryDTO> recentTasks) {
        this.recentTasks = recentTasks;
    }
}
