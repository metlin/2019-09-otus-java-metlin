package ru.otus.homework15.dao;

import ru.otus.homework15.hibernate.sessionmanager.SessionManager;
import ru.otus.homework15.model.User;

import java.util.List;

public interface UserDao {
    long create(User objectData);
    long update(User objectData);
    void createOrUpdate(User objectData);
    User load(long id);
    List<User> getAll();
    SessionManager getSessionManager();
}
