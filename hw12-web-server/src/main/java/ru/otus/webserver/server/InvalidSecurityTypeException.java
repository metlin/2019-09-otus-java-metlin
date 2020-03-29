package ru.otus.webserver.server;

public class InvalidSecurityTypeException extends RuntimeException {
    InvalidSecurityTypeException(SecurityType securityType) {
        super(String.format("Invalid security type exception: %s", securityType));
    }
}
