package controller;

import dtos.requests.LoginRequestDTO;
import dtos.requests.RegisterRequestDTO;
import dtos.responses.UserDTO;
import services.UserService;
import services.UserServiceImpl;

public class UserController {
    private final UserService userService = new UserServiceImpl();

    public UserDTO register(RegisterRequestDTO registerRequestDTO){
        return userService.createUser(registerRequestDTO);
    }

    public UserDTO login(LoginRequestDTO loginRequestDTO){
        return userService.loginUser(loginRequestDTO);
    }

    public String delete(String email, String password){
        userService.deleteUser(email, password);
        return "Successful";
    }
}
