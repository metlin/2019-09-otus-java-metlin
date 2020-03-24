package ru.otus.java.homework13.service;

public class UserServiceException extends RuntimeException {
  public UserServiceException(Exception ex) {
    super(ex);
  }
}
