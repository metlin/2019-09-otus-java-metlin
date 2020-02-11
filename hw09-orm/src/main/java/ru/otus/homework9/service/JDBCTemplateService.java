package ru.otus.homework9.service;

import java.util.Optional;

public interface JDBCTemplateService {
    <T> void saveTemplate(T template);
    <T> Optional getTemplate(long id, Class<T> clazz);
    <T> void updateTemplate(T template);
}
