package dtos.requests;

import lombok.Getter;

@Getter
public class UpdateUserRequestDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
