package ru.otus.homework15.messagesystem;

import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message msg);
}
