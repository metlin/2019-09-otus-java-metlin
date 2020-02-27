package ru.otus.homework10.database;

public class DbServiceException extends RuntimeException {
    public DbServiceException(Exception e) {
        super(e);
    }
}
