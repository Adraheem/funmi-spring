package dtos.requests;

public class UpdateUserRequestDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
