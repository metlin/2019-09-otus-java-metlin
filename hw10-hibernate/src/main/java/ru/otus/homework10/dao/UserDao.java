package ru.otus.homework10.dao;

import ru.otus.homework10.model.User;
import ru.otus.homework10.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
    void create(User objectData);
    void update(User objectData);
    void createOrUpdate(User objectData);
    Optional<User> load(long id, Class<User> clazz);

    SessionManager getSessionManager();
}
