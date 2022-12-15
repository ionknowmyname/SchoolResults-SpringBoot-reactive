package com.faithfulolaleru.SchoolResultreactive.router;

import com.faithfulolaleru.SchoolResultreactive.handler.ScoreHandler;
import com.faithfulolaleru.SchoolResultreactive.handler.StudentHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
public class RouterConfig {

    private final StudentHandler studentHandler;

    private final ScoreHandler scoreHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/api/students", studentHandler::getAllStudents)
                .GET("/api/students/{studentClass}", studentHandler::getAllStudentsByStudentClass)
                .GET("/api/students/{id}", studentHandler::getStudentById)
                .POST("/api/students/new", studentHandler::createStudent)
                .POST("/api/scores/{studentId}/new", scoreHandler::createScoreForStudent)
                .PUT("/api/scores/{studentId}/update", scoreHandler::updateScoreForStudent)
                .GET("/api/scores/{studentId}", scoreHandler::getAllScoresByStudentId)
                .build();
    }
}
