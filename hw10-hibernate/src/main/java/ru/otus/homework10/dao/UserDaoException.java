package ru.otus.homework10.dao;

public class UserDaoException extends RuntimeException {
  public UserDaoException(Exception ex) {
    super(ex);
  }
}
