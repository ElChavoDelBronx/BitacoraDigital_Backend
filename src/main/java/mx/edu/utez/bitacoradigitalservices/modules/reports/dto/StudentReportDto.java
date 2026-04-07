package mx.edu.utez.bitacoradigitalservices.modules.reports.dto;

public class StudentReportDto {
    private String studentFullName;
    private double loggedHours;
    private double requiredHours;
    private double hoursPercentage;

    public StudentReportDto(String studentFullName, double loggedHours, double requiredHours, double hoursPercentage) {
        this.studentFullName = studentFullName;
        this.loggedHours = loggedHours;
        this.requiredHours = requiredHours;
        this.hoursPercentage = hoursPercentage;
    }

    public String getStudentFullName() { return studentFullName; }
    public void setStudentFullName(String studentFullName) { this.studentFullName = studentFullName; }

    public double getLoggedHours() { return loggedHours; }
    public void setLoggedHours(double loggedHours) { this.loggedHours = loggedHours; }

    public double getRequiredHours() { return requiredHours; }
    public void setRequiredHours(double requiredHours) { this.requiredHours = requiredHours; }

    public double getHoursPercentage() { return hoursPercentage; }
    public void setHoursPercentage(double hoursPercentage) { this.hoursPercentage = hoursPercentage; }
}