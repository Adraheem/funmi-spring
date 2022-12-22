package services;

import data.model.User;
import data.repository.UserRepository;
import dtos.requests.LoginRequestDTO;
import dtos.requests.RegisterRequestDTO;
import dtos.requests.ResetPasswordRequestDTO;
import dtos.requests.UpdateUserRequestDTO;
import dtos.responses.UserDTO;
import exception.SignupSignInException;

public class UserServiceImpl implements UserService {

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
    public UserDTO loginUser(LoginRequestDTO loginRequestDTO) throws SignupSignInException {
        if(!loginRequestDTO.getPassword().equals(loginRequestDTO.getConfirmPassword()))
            throw new SignupSignInException("Password not matching");

        User user = validateUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if (user == null) throw new IllegalArgumentException("Incorrect old password");

        UserDTO res = new UserDTO();
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setEmail(user.getEmail());
        return res;
    }

    @Override
    public UserDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        User user = userRepository.findByKey(updateUserRequestDTO.getEmail());
        if (updateUserRequestDTO.getFirstName() != null) user.setFirstName(updateUserRequestDTO.getFirstName());
        if (updateUserRequestDTO.getLastName() != null) user.setLastName(updateUserRequestDTO.getLastName());
        if (updateUserRequestDTO.getPhoneNumber() != null) user.setPhoneNumber(updateUserRequestDTO.getPhoneNumber());
        User savedUser = userRepository.save(user);

        UserDTO res = new UserDTO();
        res.setFirstName(savedUser.getFirstName());
        res.setLastName(savedUser.getLastName());
        res.setEmail(savedUser.getEmail());
        return res;
    }

    @Override
    public void deleteUser(String email, String password) {
        User user = validateUser(email, password);
        if (user != null) userRepository.delete(email);
    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) throws SignupSignInException {
        User user = validateUser(resetPasswordRequestDTO.getEmail(), resetPasswordRequestDTO.getOldPassword());
        if (user == null) throw new SignupSignInException("Incorrect old password");

        user.setPassword(resetPasswordRequestDTO.getNewPassword());
        userRepository.save(user);
    }

    private User validateUser(String email, String password) {
        User user = userRepository.findByKey(email);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }
}
