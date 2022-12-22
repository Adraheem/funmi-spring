package dtos.requests;

import lombok.Getter;

@Getter
public class LoginRequestDTO {
    private String email;
    private String password;
    private String confirmPassword;
}
