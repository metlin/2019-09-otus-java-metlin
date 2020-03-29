package ru.otus.webserver.services;

import ru.otus.homework11.model.Admin;
import ru.otus.homework11.model.User;
import java.util.List;

public class UserAuthServiceImpl implements UserAuthService {

    private final List<User> allUsers;
    private Admin admin;

    public UserAuthServiceImpl(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    @Override
    public boolean authenticate(String login, String password) {
        admin = new Admin();
        return admin.getLogin().equals(login) && admin.getPassword().equals(password);
    }
}
