package IoC.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.java.homework13.hibernate.HibernateUtils;
import ru.otus.java.homework13.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.java.homework13.hibernate.sessionmanager.SessionManager;
import ru.otus.java.homework13.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.java.homework13.model.AddressDataSet;
import ru.otus.java.homework13.model.PhoneDataSet;
import ru.otus.java.homework13.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionManager sessionManager;

    public UserDaoImpl() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("WEB-INF/static/hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);
        this.sessionManager = new SessionManagerHibernate(sessionFactory);
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
            logger.error("Failed to create user", e);
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
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return currentSession.getHibernateSession().find(User.class, id);
        } catch (Exception e) {
            logger.error("user with id " + id + " not found", e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
