package com.faithfulolaleru.SchoolResultreactive.router;

import com.faithfulolaleru.SchoolResultreactive.handler.StudentHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
public class StudentRouterConfig {

    private final StudentHandler studentHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/api/students/", studentHandler::getAllStudents)
                .GET("/api/students/{id}", studentHandler::getStudentById)
                .POST("/api/students/new", studentHandler::createStudent)
                .build();
    }
}
