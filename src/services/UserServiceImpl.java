package services;

import data.model.User;
import data.repository.UserRepository;
import data.repository.UserRepositoryInterface;
import dtos.requests.LoginRequestDTO;
import dtos.requests.RegisterRequestDTO;
import dtos.responses.UserDTO;

public class UserServiceImpl implements UserService, UserRepositoryInterface {

    private final UserRepository userRepository = new UserRepository();

    @Override
    public UserDTO createUser(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByKey(registerRequestDTO.getEmail()) != null) throw new IllegalArgumentException("");

        User user = new User(
                registerRequestDTO.getFirstName(),
                registerRequestDTO.getLastName(),
                registerRequestDTO.getEmail(),
                registerRequestDTO.getPassword(),
                registerRequestDTO.getPhoneNumber()
        );

        User savedUser = userRepository.save(user);
        UserDTO res = new UserDTO();
        res.setFirstName(savedUser.getFirstName());
        res.setLastName(savedUser.getLastName());
        res.setEmail(savedUser.getEmail());
        return res;
    }

    @Override
    public UserDTO loginUser(LoginRequestDTO loginRequestDTO) {
        User user = validateUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        UserDTO res = new UserDTO();
        assert user != null;
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setEmail(user.getEmail());
        return res;
    }

    @Override
    public void deleteUser(String email, String password) {
        User user = validateUser(email, password);
        if (user != null) userRepository.delete(email);
    }

    private User validateUser(String email, String password) {
        User user = userRepository.findByKey(email);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }
}
