package com.irme.server.webapp.jwt;

public class JWTAuthenticationException extends RuntimeException {

    public JWTAuthenticationException(String message) {
        super(message);
    }
}
