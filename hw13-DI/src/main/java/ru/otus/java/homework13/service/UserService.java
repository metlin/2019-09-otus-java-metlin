package ru.otus.java.homework13.service;

import ru.otus.java.homework13.model.User;

import java.util.List;

public interface UserService {
    long saveUser(User user);
    User getUser(long id);
    long updateUser(User user);
    List<User> getAllUsers();
}
