package dtos.requests;

import lombok.Getter;

@Getter
public class ResetPasswordRequestDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
}
