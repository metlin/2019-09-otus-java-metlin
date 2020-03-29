package ru.otus.webserver.services;

import java.io.IOException;

public interface TemplateProcessor {
    <T> String getPage(String filename, T data) throws IOException;
}
