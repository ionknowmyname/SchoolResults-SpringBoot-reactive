package com.faithfulolaleru.SchoolResultreactive.handler.service;

import com.faithfulolaleru.SchoolResultreactive.exception.GeneralException;
import com.faithfulolaleru.SchoolResultreactive.models.Student;
import com.faithfulolaleru.SchoolResultreactive.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public record StudentService(StudentRepository studentRepository) {

    public Mono<Student> findStudentById(Integer id) {
        Mono<Student> student = studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new GeneralException(HttpStatus.NOT_FOUND,
                        "Student with id doesn't exist")));

        return student;
    }

    public Mono<Boolean> isStudentExist(Integer id) {
        return studentRepository.existsById(id);
    }
}
