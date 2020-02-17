package ru.otus.homework10.sessionmanager;

import ru.otus.homework10.hibernate.sessionmanager.DatabaseSessionHibernate;

public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();
    DatabaseSessionHibernate getCurrentSession();
}
