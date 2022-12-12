package com.faithfulolaleru.SchoolResultreactive.utils;

import com.faithfulolaleru.SchoolResultreactive.dtos.StudentRequest;
import com.faithfulolaleru.SchoolResultreactive.dtos.StudentResponse;
import com.faithfulolaleru.SchoolResultreactive.models.Student;
import com.faithfulolaleru.SchoolResultreactive.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Component
public class AppUtils {

    private final StudentRepository studentRepository;

    public static StudentResponse entityToDto(Student student) {
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(student, response);

        return response;
    }

    public static Student dtoToEntity(StudentRequest request) {
        Student student = new Student();
        BeanUtils.copyProperties(request, student);

        return student;
    }




    /*public static Mono<AppResponse> buildAppResponse(Object data, String message) {   // Object can also be List
        return Mono.just(AppResponse.builder()
                .statusCode("200")
                .httpStatus(HttpStatus.OK)
                .message(message)
                .data(data)
                .build());
    }

    public static Mono<AppResponse> buildAppResponse(String message) {   // Object can also be List
        return Mono.just(AppResponse.builder()
                .statusCode("200")
                .httpStatus(HttpStatus.OK)
                .message(message)
                .data(null)
                .build());
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return findUserByUsername(username);
    }

    public User findUserByUsername(@NotNull String username) {
        Mono<User> userMono = userRepository.findByUsername(username);

        return userMono.block();
    }*/
}
