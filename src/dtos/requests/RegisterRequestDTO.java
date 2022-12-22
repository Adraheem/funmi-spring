package dtos.requests;

import lombok.Getter;

@Getter
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}