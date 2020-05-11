package ru.otus.homework15.service;

import ru.otus.homework15.dao.UserDao;
import ru.otus.homework15.hibernate.sessionmanager.SessionManager;
import ru.otus.homework15.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.create(user);
                sessionManager.commitSession();
                return userId;
            } catch (Exception e) {
                logger.error("Failed to create user", e);
                sessionManager.rollbackSession();
                throw new UserServiceException(e);
            }
        }
    }

    @Override
    public User getUser(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.load(id);
            } catch (Exception e) {
                logger.error("user with id " + id + " not found", e);
                sessionManager.rollbackSession();
                throw new UserServiceException(e);
            }
        }
    }

    @Override
    public long updateUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.update(user);
            } catch (Exception e) {
                logger.error("Failed to update user with id = " + user.getId(), e);
                sessionManager.rollbackSession();
                throw new UserServiceException(e);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            return userDao.getAll();
        }
    }
}

