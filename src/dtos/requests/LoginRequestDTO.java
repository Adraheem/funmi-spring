package dtos.requests;

public class LoginRequestDTO {
    private String email;
    private String password;

    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
