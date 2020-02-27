package ru.otus.homework10.service;

import ru.otus.homework10.model.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    Optional getTemplate(long id, Class<User> clazz);
    void updateTemplate(User user);
}
