package ru.otus.homework9.dao;

import ru.otus.homework9.sessionmanager.SessionManager;
import ru.otus.homework9.model.User;

import java.util.Optional;

public interface JDBCTemplateDAO<T> {
    void create(T objectData);
    void update(T objectData);
    void createOrUpdate(T objectData);
    Optional<T> load(long id, Class<T> clazz);

    SessionManager getSessionManager();
}
