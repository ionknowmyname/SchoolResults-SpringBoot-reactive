package com.faithfulolaleru.SchoolResultreactive.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);

        Map<String, Object> map2 = super.getErrorAttributes(request, options);

        // log.info("map --> ", map2.containsKey("status"));
        System.out.println(map2);

        Map<String, Object> map = new HashMap<>();
        map.put("httpStatus", HttpStatus.BAD_REQUEST.value());  // 404
        map.put("status", HttpStatus.BAD_REQUEST);  // "NOT FOUND"
        map.put("message", error.getMessage());
        map.put("endpoint", request.path());
        // request.

        // error.printStackTrace();

        // log.info("error -->", error.getCause());

        return map;
    }
}
