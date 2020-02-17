package ru.otus.homework10.service;

import ru.otus.homework10.dao.UserDao;
import ru.otus.homework10.database.DbServiceException;
import ru.otus.homework10.model.User;
import ru.otus.homework10.sessionmanager.SessionManager;

import java.util.Optional;

public class UserServiceImpl implements UserService {

   private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                userDao.create(user);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional getTemplate(long id, Class<User> clazz) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.load(id, clazz);
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }

        return Optional.empty();
    }

    @Override
    public void updateTemplate(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                userDao.update(user);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }
}
