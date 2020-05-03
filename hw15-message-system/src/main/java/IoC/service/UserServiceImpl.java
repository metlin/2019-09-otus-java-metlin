package IoC.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.java.homework13.cachehw.HwCache;
import ru.otus.java.homework13.dao.UserDao;
import ru.otus.java.homework13.hibernate.sessionmanager.SessionManager;
import ru.otus.java.homework13.model.User;

import java.util.ArrayList;
import java.util.List;

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
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.create(user);
                cache.put(userId, user);
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
                return getValue(id);
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
                long userId = userDao.update(user);
                cache.put(userId, user);
                return userId;
            } catch (Exception e) {
                logger.error("Failed to update user with id = " + user.getId(), e);
                sessionManager.rollbackSession();
                throw new UserServiceException(e);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        int count = 1;
        while (getUser(count) != null){
            userList.add(getUser(count));
            count++;
        }

        return userList;
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

