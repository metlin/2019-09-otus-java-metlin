package ru.otus.homework15.service;

public class UserServiceException extends RuntimeException {
  public UserServiceException(Exception ex) {
    super(ex);
  }
}
