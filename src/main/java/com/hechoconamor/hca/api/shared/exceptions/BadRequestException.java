package com.hechoconamor.hca.api.shared.exceptions;

public class BadRequestException extends IllegalArgumentException {

    public BadRequestException(String message) {
        super(message);
    }

}
