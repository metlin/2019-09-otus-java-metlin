package ru.otus.homework11.service;

import ru.otus.homework11.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long saveUser(User user);
    User getUser(long id);
    long updateUser(User user);
    List<User> getAllUsers();
}
