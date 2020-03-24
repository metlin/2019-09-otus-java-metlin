package ru.otus.java.homework13.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.java.homework13.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long create(User user) {
        try {
            if (user.getId() > 0) {
                sessionFactory.getCurrentSession().merge(user);
            } else {
                sessionFactory.getCurrentSession().persist(user);
            }
            return user.getId();
        } catch (Exception e) {
            logger.error("Failed to create user", e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public long update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            return user.getId();
        } catch (Exception e) {
            logger.error("Failed to update user with id = " + user.getId(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public void createOrUpdate(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User load(long id) {
        try {
            return sessionFactory.getCurrentSession().find(User.class, id);
        } catch (Exception e) {
            logger.error("user with id " + id + " not found", e);
            throw new UserDaoException(e);
        }
    }
}
