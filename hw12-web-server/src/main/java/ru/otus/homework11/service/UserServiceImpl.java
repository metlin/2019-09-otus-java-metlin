package ru.otus.homework11.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework11.cachehw.HwCache;
import ru.otus.homework11.cachehw.HwListener;
import ru.otus.homework11.dao.UserDao;
import ru.otus.homework11.database.DbServiceException;
import ru.otus.homework11.model.User;
import ru.otus.homework11.sessionmanager.SessionManager;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private HwCache<Long, User> cache;

    public UserServiceImpl(UserDao userDao, HwCache<Long, User> cache) {
        this.userDao = userDao;
        this.cache = cache;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.create(user);
                cache.addListener(addNewListener());
                cache.put(userId, user);
                sessionManager.commitSession();
                return userId;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public User getTemplate(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return getValue(id);
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }

        return null;
    }

    @Override
    public long updateTemplate(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.update(user);
                cache.addListener(addNewListener());
                cache.put(userId, user);
                sessionManager.commitSession();
                return userId;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    private User getValue(long id) {
        //with cache
        User value = cache.get(id);
        if (value == null) {
            value = userDao.load(id);
            cache.put(id, value);
        }

        //without cache
//        User value = userDao.load(id);

        return value;
    }

    private HwListener addNewListener() {
        return (HwListener<Long, User>) (key, value, action) -> logger.info("key:{}, value:{}, action: {}", key, value, action);
    }
}

