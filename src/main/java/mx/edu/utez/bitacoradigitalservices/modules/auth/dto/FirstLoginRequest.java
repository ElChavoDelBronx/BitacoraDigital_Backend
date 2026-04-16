package mx.edu.utez.bitacoradigitalservices.modules.auth.dto;

public class FirstLoginRequest {
    private Long userId;
    private String newPassword;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
