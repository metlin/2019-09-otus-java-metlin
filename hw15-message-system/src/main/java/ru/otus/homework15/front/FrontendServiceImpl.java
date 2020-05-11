package ru.otus.homework15.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.otus.homework15.messagesystem.Message;
import ru.otus.homework15.messagesystem.MessageType;
import ru.otus.homework15.messagesystem.MsClient;
import ru.otus.homework15.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(@Qualifier("frontendMsClient") @Lazy MsClient msClient,
                               @Value("${backendServiceClientName}") String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void getUserData(long userId, Consumer<String> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, userId, MessageType.USER_DATA);
        logger.info("outMessage{}", outMsg);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void saveUser(User user, Consumer<String> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, user, MessageType.USER_CREATE);
        logger.info("save user {}", outMsg);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void getAllUsers(Consumer<List<User>> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, null, MessageType.USER_DATA_COLLECTION);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null) {
            logger.warn("consumer not found for:{}", sourceMessageId);
            return Optional.empty();
        }
        return Optional.of(consumer);
    }
}
