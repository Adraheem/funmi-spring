package services;

import data.model.User;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password, String phoneNumber);

    User loginUser(String email, String password);

    void deleteUser(String email, String password);
}
