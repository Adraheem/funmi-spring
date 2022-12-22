package services;

import dtos.requests.LoginRequestDTO;
import dtos.requests.RegisterRequestDTO;
import dtos.requests.ResetPasswordRequestDTO;
import dtos.requests.UpdateUserRequestDTO;
import dtos.responses.UserDTO;

public interface UserService {
    UserDTO createUser(RegisterRequestDTO registerRequestDTO);

    UserDTO loginUser(LoginRequestDTO loginRequestDTO);

    UserDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO);

    void deleteUser(String email, String password);

    void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);
}
