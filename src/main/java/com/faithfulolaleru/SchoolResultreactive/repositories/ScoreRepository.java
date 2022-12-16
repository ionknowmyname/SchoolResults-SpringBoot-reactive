package com.faithfulolaleru.SchoolResultreactive.repositories;

import com.faithfulolaleru.SchoolResultreactive.models.Score;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ScoreRepository extends ReactiveCrudRepository<Score, String> {

    Mono<Score> findByStudentIdAndTerm(String studentId, int term);

    Flux<Score> findAllByStudentId(String studentId);
}
