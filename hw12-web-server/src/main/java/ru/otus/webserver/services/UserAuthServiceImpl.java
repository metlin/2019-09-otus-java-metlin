package ru.otus.webserver.services;

import ru.otus.homework11.model.User;
import java.util.List;

public class UserAuthServiceImpl implements UserAuthService {

    private final List<User> allUsers;

    public UserAuthServiceImpl(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    @Override
    public boolean authenticate(String login, String password) {
        for(User user : allUsers) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
