package com.hechoconamor.hca.api.shared.exceptions;

public class ConflictException extends IllegalArgumentException {

    public ConflictException(String message) {
        super(message);
    }

}
