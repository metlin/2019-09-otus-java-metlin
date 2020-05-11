package ru.otus.homework15.dao;

public class UserDaoException extends RuntimeException {
  public UserDaoException(Exception ex) {
    super(ex);
  }
}
