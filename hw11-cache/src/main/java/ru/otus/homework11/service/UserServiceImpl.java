package ru.otus.homework11.service;

import ru.otus.homework11.cachehw.HwCache;
import ru.otus.homework11.cachehw.MyCache;
import ru.otus.homework11.dao.UserDao;
import ru.otus.homework11.database.DbServiceException;
import ru.otus.homework11.model.User;
import ru.otus.homework11.sessionmanager.SessionManager;

import java.util.Optional;

public class UserServiceImpl implements UserService {

   private UserDao userDao;
   private HwCache<Long, User> cache = new MyCache<>();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.create(user);
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
    public Optional<User> getTemplate(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return getValue(id);
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }

        return Optional.empty();
    }

    @Override
    public long updateTemplate(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.update(user);
                sessionManager.commitSession();
                return userId;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    private Optional<User> getValue(long id) {

        //with cache
//        User value = cache.get(id);
//        if (value == null) {
//            value = userDao.load(id);
//            cache.put(id, value);
//        }

        //without cache
        User value = userDao.load(id);

        return Optional.ofNullable(value);
    }

    private long updateValue(long id) {
       return 1L;
    }
}

