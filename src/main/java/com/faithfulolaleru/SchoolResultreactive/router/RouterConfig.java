package com.faithfulolaleru.SchoolResultreactive.router;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/")
    }
}
