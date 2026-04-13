package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ActiveProjectsAndStudents;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ProjectProgressProjection;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.RecentEvidences;

import java.util.List;
import java.util.Map;

public class AdminDashboardDTO {
    private AdminStatisticsDTO stats;
    private List<RecentEvidences> recentEvidences;
    private long completedTasks;
    private List<ProjectProgressProjection> advance;

    public AdminDashboardDTO(AdminStatisticsDTO stats, List<RecentEvidences> recentEvidences, long completedTasks, List<ProjectProgressProjection> advance) {
        this.stats = stats;
        this.recentEvidences = recentEvidences;
        this.completedTasks = completedTasks;
        this.advance = advance;
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

    public void setCompletedTasks(long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public List<ProjectProgressProjection> getAdvance() {
        return advance;
    }

    public void setAdvance(List<ProjectProgressProjection> advance) {
        this.advance = advance;
    }
}
