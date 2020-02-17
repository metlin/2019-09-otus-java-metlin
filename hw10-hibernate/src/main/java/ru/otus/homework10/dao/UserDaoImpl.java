package ru.otus.homework10.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework10.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.homework10.model.User;
import ru.otus.homework10.sessionmanager.SessionManager;

import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionManager sessionManager;

    public UserDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void create(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public void update(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.update(user);
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public void createOrUpdate(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> load(long id, Class<User> clazz) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(clazz, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
