package com.faithfulolaleru.SchoolResultreactive.repositories;

import com.faithfulolaleru.SchoolResultreactive.models.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {

    Mono<Student> findStudentByName(String name);
}
