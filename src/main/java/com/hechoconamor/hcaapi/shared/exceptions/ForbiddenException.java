package com.hechoconamor.hcaapi.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN) // 403
public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }

}
