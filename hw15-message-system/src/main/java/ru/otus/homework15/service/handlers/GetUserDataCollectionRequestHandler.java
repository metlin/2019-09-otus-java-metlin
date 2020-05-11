package ru.otus.homework15.service.handlers;

import org.springframework.stereotype.Component;
import ru.otus.homework15.app.common.Serializers;
import ru.otus.homework15.messagesystem.Message;
import ru.otus.homework15.messagesystem.MessageType;
import ru.otus.homework15.messagesystem.RequestHandler;
import ru.otus.homework15.model.User;
import ru.otus.homework15.service.UserService;

import java.util.List;
import java.util.Optional;

@Component
public class GetUserDataCollectionRequestHandler implements RequestHandler {

    private final UserService userService;

    public GetUserDataCollectionRequestHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        List<User> users = userService.getAllUsers();
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(),
                MessageType.USER_DATA_COLLECTION.getValue(), Serializers.serialize(users)));
    }
}
