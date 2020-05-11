package ru.otus.homework15;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework15.front.FrontendService;
import ru.otus.homework15.front.handlers.GetUserDataCollectionResponseHandler;
import ru.otus.homework15.front.handlers.CreateUserResponseHandler;
import ru.otus.homework15.messagesystem.MessageSystem;
import ru.otus.homework15.messagesystem.MessageType;
import ru.otus.homework15.messagesystem.MsClient;
import ru.otus.homework15.messagesystem.MsClientImpl;
import ru.otus.homework15.service.UserService;
import ru.otus.homework15.service.handlers.GetUserDataCollectionRequestHandler;
import ru.otus.homework15.service.handlers.CreateUserRequestHandler;

@Configuration
public class Config {

    private final MessageSystem messageSystem;

    public Config(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    @Bean
    public MsClient backendMsClient(UserService userService,
                                    MessageSystem messageSystem,
                                    @Value("${backendServiceClientName}") String backendServiceClientName) {
        MsClient client = new MsClientImpl(backendServiceClientName, messageSystem);
        client.addHandler(MessageType.USER_DATA, new CreateUserRequestHandler(userService));
        client.addHandler(MessageType.USER_DATA_COLLECTION, new GetUserDataCollectionRequestHandler(userService));
        messageSystem.addClient(client);

        return client;
    }

    @Bean
    public MsClient frontendMsClient(FrontendService frontendService,
                                     MessageSystem messageSystem,
                                     @Value("${frontendServiceClientName}") String frontendServiceClientName) {
        MsClient client = new MsClientImpl(frontendServiceClientName, messageSystem);
        client.addHandler(MessageType.USER_DATA, new CreateUserResponseHandler(frontendService));
        client.addHandler(MessageType.USER_DATA_COLLECTION, new GetUserDataCollectionResponseHandler(frontendService));
        messageSystem.addClient(client);

        return client;
    }
}
