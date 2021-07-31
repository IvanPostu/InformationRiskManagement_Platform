package com.irme.server.dal.exceptions;

import lombok.Getter;

public class DataAccessLayerException extends Exception {

    @Getter
    private DataAccessErrorCode errorCode;

    public DataAccessLayerException(String message, DataAccessErrorCode errorCode) {
        super(message);

        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("Data access exception, Error code: ")
                .append(this.errorCode.toString())
                .append(" Message: ")
                .append(super.getMessage());

        return messageBuilder.toString();
    }
}
