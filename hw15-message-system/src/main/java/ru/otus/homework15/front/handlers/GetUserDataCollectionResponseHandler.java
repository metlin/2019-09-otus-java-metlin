package ru.otus.homework15.front.handlers;

import ru.otus.homework15.app.common.Serializers;
import ru.otus.homework15.front.FrontendService;
import ru.otus.homework15.messagesystem.Message;
import ru.otus.homework15.messagesystem.RequestHandler;
import ru.otus.homework15.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GetUserDataCollectionResponseHandler implements RequestHandler {

    private final FrontendService frontendService;

    public GetUserDataCollectionResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
//        logger.info("xz{}", msg);
        try {
            List<User> users = Serializers.deserialize(msg.getPayload(), List.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            frontendService.takeConsumer(sourceMessageId, List.class).ifPresent(consumer -> consumer.accept(users));

        } catch (Exception ex) {
//            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }
}
