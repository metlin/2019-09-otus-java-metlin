package IoC.service;

public class UserServiceException extends RuntimeException {
  public UserServiceException(Exception ex) {
    super(ex);
  }
}
