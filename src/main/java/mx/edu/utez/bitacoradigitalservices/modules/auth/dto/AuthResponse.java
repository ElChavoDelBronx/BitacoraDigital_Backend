package mx.edu.utez.bitacoradigitalservices.modules.auth.dto;

public class AuthResponse {
    private String token;
    private String role;
    private Long userId;
    private String userName;

    public AuthResponse(String token, String role, Long userId, String userName) {
        this.token = token;
        this.role = role;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}