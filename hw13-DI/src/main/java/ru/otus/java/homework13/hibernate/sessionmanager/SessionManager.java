package ru.otus.java.homework13.hibernate.sessionmanager;


public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();
    DatabaseSessionHibernate getCurrentSession();
}
