package ru.otus.homework15.hibernate.sessionmanager;

public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();
    DatabaseSessionHibernate getCurrentSession();
}
