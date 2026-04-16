package mx.edu.utez.bitacoradigitalservices.modules.auth.dto;

public class AuthResponse {
    private String token;
    private String role;
    private Long userId;
    private String userName;

    private boolean firstSigIn;

    public AuthResponse() {
    }


    public AuthResponse(String token, String role, Long userId, String userName, boolean firstSignIn) {

        this.token = token;
        this.role = role;
        this.userId = userId;
        this.userName = userName;
        this.firstSigIn = firstSigIn;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public boolean isFirstSigIn() { return firstSigIn; }
    public void setFirstSigIn(boolean firstSigIn) { this.firstSigIn = firstSigIn; }
}


