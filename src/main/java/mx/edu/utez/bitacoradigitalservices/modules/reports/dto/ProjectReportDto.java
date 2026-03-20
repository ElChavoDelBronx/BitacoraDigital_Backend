package mx.edu.utez.bitacoradigitalservices.modules.reports.dto;

public class ProjectReportDto {
    private String projectName;
    private long totalTasks;
    private long completedTasks;
    private double completionPercentage;

    public ProjectReportDto(String projectName, long totalTasks, long completedTasks, double completionPercentage) {
        this.projectName = projectName;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.completionPercentage = completionPercentage;
    }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public long getTotalTasks() { return totalTasks; }
    public void setTotalTasks(long totalTasks) { this.totalTasks = totalTasks; }

    public long getCompletedTasks() { return completedTasks; }
    public void setCompletedTasks(long completedTasks) { this.completedTasks = completedTasks; }

    public double getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(double completionPercentage) { this.completionPercentage = completionPercentage; }
}