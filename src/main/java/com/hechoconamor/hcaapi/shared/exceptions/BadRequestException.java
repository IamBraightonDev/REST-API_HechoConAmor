package com.hechoconamor.hcaapi.shared.exceptions;

public class BadRequestException extends IllegalArgumentException {

    public BadRequestException(String message) {
        super(message);
    }

}
