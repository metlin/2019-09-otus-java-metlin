package ru.otus.homework15.service.handlers;

import org.springframework.stereotype.Component;
import ru.otus.homework15.messagesystem.Message;
import ru.otus.homework15.messagesystem.MessageType;
import ru.otus.homework15.messagesystem.RequestHandler;
import ru.otus.homework15.model.User;
import ru.otus.homework15.service.UserService;
import ru.otus.homework15.app.common.Serializers;
import java.util.Optional;

@Component
public class CreateUserRequestHandler implements RequestHandler {
    private final UserService userService;

    public CreateUserRequestHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        User user = Serializers.deserialize(msg.getPayload(), User.class);
        userService.saveUser(user);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_CREATE.getValue(), Serializers.serialize(user)));
    }
}
