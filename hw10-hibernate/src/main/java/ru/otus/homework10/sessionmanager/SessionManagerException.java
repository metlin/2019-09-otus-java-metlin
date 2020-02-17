package ru.otus.homework10.sessionmanager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg) {
        super(msg);
    }
    public SessionManagerException(Exception ex) {
        super(ex);
    }
}
