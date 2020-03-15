package ru.otus.homework11.dao;

import ru.otus.homework11.model.User;
import ru.otus.homework11.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
    long create(User objectData);
    long update(User objectData);
    void createOrUpdate(User objectData);
    User load(long id);

    SessionManager getSessionManager();
}
