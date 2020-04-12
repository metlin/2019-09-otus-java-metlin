package ru.otus.java.homework13.dao;

import ru.otus.java.homework13.hibernate.sessionmanager.SessionManager;
import ru.otus.java.homework13.model.User;

public interface UserDao {
    long create(User objectData);
    long update(User objectData);
    void createOrUpdate(User objectData);
    User load(long id);
    SessionManager getSessionManager();
}
