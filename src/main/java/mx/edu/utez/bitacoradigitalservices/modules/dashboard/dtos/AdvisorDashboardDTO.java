package mx.edu.utez.bitacoradigitalservices.modules.dashboard.dtos;

import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ProjectProgressProjection;
import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.RecentEvidences;
import java.util.List;

public class AdvisorDashboardDTO {
    private AdvisorStatisticsDTO stats;
    private List<RecentEvidences> recentEvidences;
    private List<ProjectProgressProjection> advance;

    public AdvisorDashboardDTO(AdvisorStatisticsDTO stats, List<RecentEvidences> recentEvidences, List<ProjectProgressProjection> advance) {
        this.stats = stats;
        this.recentEvidences = recentEvidences;
        this.advance = advance;
    }

    public AdvisorStatisticsDTO getStats() { return stats; }
    public void setStats(AdvisorStatisticsDTO stats) { this.stats = stats; }
    public List<RecentEvidences> getRecentEvidences() { return recentEvidences; }
    public void setRecentEvidences(List<RecentEvidences> recentEvidences) { this.recentEvidences = recentEvidences; }
    public List<ProjectProgressProjection> getAdvance() { return advance; }
    public void setAdvance(List<ProjectProgressProjection> advance) { this.advance = advance; }
}