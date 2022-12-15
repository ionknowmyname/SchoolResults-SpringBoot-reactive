package com.faithfulolaleru.SchoolResultreactive.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
