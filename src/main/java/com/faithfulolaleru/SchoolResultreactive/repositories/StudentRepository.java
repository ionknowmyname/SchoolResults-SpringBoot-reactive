package com.faithfulolaleru.SchoolResultreactive.repositories;

import com.faithfulolaleru.SchoolResultreactive.models.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface StudentRepository extends ReactiveCrudRepository<Student, String> {

    Mono<Student> findStudentByName(String name);

    Flux<Student> findStudentsByStudentClass(String studentClass);
}
