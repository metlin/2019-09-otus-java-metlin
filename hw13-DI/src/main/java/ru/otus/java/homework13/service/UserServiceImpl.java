package ru.otus.java.homework13.service;

import org.springframework.stereotype.Service;
import ru.otus.java.homework13.cachehw.HwCache;
import ru.otus.java.homework13.dao.UserDao;
import ru.otus.java.homework13.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private HwCache<Long, User> cache;

    public UserServiceImpl(UserDao userDao, HwCache<Long, User> cache) {
        this.userDao = userDao;
        this.cache = cache;
    }

    @Override
    @Transactional
    public long saveUser(User user) {
        try {
            long userId = userDao.create(user);
            cache.put(userId, user);
            return userId;
        } catch (Exception e) {
            logger.error("Failed to create user", e);
            throw new UserServiceException(e);
        }
    }

    @Override
    @Transactional
    public User getUser(long id) {
        try {
            return getValue(id);
        } catch (Exception e) {
           logger.error("user with id " + id + " not found", e);
           throw new UserServiceException(e);
        }
    }

    @Override
    @Transactional
    public long updateUser(User user) {
        try {
            long userId = userDao.update(user);
            cache.put(userId, user);
            return userId;
        } catch (Exception e) {
            logger.error("Failed to update user with id = " + user.getId(), e);
            throw new UserServiceException(e);
        }
    }

    private User getValue(long id) {
        User value = cache.get(id);
        if (value == null) {
            value = userDao.load(id);
            cache.put(id, value);
        }

        return value;
    }
}

