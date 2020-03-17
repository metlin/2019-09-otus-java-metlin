package ru.otus.homework11.service;

import ru.otus.homework11.model.User;

import java.util.Optional;

public interface UserService {
    long saveUser(User user);
    User getTemplate(long id);
    long updateTemplate(User user);
}
