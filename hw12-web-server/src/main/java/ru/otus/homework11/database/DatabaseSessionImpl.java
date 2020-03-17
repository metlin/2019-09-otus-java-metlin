package ru.otus.homework11.database;

import java.sql.Connection;

public class DatabaseSessionImpl {
    private final Connection connection;

    public DatabaseSessionImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
