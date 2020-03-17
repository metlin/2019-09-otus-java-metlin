package ru.otus.homework11.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework11.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.homework11.model.User;
import ru.otus.homework11.sessionmanager.SessionManager;

public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionManager sessionManager;

    public UserDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public long create(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }

            return user.getId();
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public long update(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.update(user);
            return user.getId();
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public void createOrUpdate(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User load(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return currentSession.getHibernateSession().find(User.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
