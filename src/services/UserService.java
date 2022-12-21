package services;

import dtos.requests.LoginRequestDTO;
import dtos.requests.RegisterRequestDTO;
import dtos.responses.UserDTO;

public interface UserService {
    UserDTO createUser(RegisterRequestDTO registerRequestDTO);

    UserDTO loginUser(LoginRequestDTO loginRequestDTO);

    void deleteUser(String email, String password);
}
