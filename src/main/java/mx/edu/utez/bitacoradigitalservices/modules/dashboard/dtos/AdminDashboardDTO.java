package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ActiveProjectsAndStudents;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.RecentEvidences;

import java.util.List;
import java.util.Map;

public class AdminDashboardDTO {
    private AdminStatisticsDTO stats;
    private List<RecentEvidences> recentEvidences;
    private long completedTasks;

    public AdminDashboardDTO(AdminStatisticsDTO stats, List<RecentEvidences> recentEvidences, long completedTasks) {
        this.stats = stats;
        this.recentEvidences = recentEvidences;
        this.completedTasks = completedTasks;
    }

    public AdminStatisticsDTO getStats() {
        return stats;
    }

    public void setStats(AdminStatisticsDTO stats) {
        this.stats = stats;
    }

    public List<RecentEvidences> getRecentEvidences() {
        return recentEvidences;
    }

    public void setRecentEvidences(List<RecentEvidences> recentEvidences) {
        this.recentEvidences = recentEvidences;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public void setcompletedTasks(long completedTasks) {
        this.completedTasks = completedTasks;
    }
}
