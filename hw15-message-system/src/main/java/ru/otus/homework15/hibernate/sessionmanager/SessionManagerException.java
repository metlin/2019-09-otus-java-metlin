package ru.otus.homework15.hibernate.sessionmanager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg) {
        super(msg);
    }
    public SessionManagerException(Exception ex) {
        super(ex);
    }
}
