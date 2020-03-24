package ru.otus.java.homework13.service;

import ru.otus.java.homework13.model.User;

public interface UserService {
    long saveUser(User user);
    User getUser(long id);
    long updateUser(User user);
}
