package com.faithfulolaleru.SchoolResultreactive.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
