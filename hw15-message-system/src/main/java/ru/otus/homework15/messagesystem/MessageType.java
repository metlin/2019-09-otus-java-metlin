package ru.otus.homework15.messagesystem;

public enum MessageType {
    USER_DATA("UserData"),
    USER_DATA_COLLECTION("UserCollection"),
    USER_CREATE("CreateUser");

    private final String value;

    MessageType(String value) {
    this.value = value;
  }

    public String getValue() {
    return value;
  }
}
