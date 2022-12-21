package services;

import data.model.User;
import data.repository.UserRepository;
import data.repository.UserRepositoryInterface;

public class UserServiceImpl implements UserService, UserRepositoryInterface {

    private final UserRepository userRepository = new UserRepository();

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String phoneNumber) {
        if (userRepository.findByKey(email) != null) throw new IllegalArgumentException("");

        User user = new User(firstName, lastName, email, password, phoneNumber);
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return validateUser(email, password);
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
