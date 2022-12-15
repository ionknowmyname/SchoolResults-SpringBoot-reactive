package com.faithfulolaleru.SchoolResultreactive.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;


@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {


    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
                                          ServerCodecConfigurer configurer, ApplicationContext applicationContext) {

        super(errorAttributes, resources, applicationContext);
//        super.setMessageWriters(configurer.getWriters());
//        super.setMessageReaders(configurer.getReaders());

        this.setMessageReaders(configurer.getReaders());
        this.setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        int statusCode = Integer.parseInt(errorPropertiesMap.get("httpStatus").toString());

        return ServerResponse.status(HttpStatus.valueOf(statusCode))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }
}
