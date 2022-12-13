package com.faithfulolaleru.SchoolResultreactive.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);

        // Map<String, Object> map = super.getErrorAttributes(request, options);

        Map<String, Object> map = new HashMap<>();
        map.put("httpStatus", HttpStatus.NOT_FOUND.value());  // 404
        map.put("status", HttpStatus.NOT_FOUND);  // "NOT FOUND"
        map.put("message", error.getMessage());
        map.put("endpoint", request.path());

        return map;
    }
}
