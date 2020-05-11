package ru.otus.homework15.front;

import ru.otus.homework15.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {
    void getUserData(long userId, Consumer<String> dataConsumer);
    void saveUser(User user, Consumer<String> dataConsumer);
    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
    void getAllUsers(Consumer<List<User>> dataConsumer);
}

