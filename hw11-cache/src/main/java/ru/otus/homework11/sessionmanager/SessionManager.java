package ru.otus.homework11.sessionmanager;


import ru.otus.homework11.hibernate.sessionmanager.DatabaseSessionHibernate;

public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();
    DatabaseSessionHibernate getCurrentSession();
}
