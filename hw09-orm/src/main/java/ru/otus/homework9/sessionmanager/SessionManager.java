package ru.otus.homework9.sessionmanager;

import ru.otus.homework9.database.DatabaseSession;

public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();
    DatabaseSession getCurrentSession();
}
