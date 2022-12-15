package com.faithfulolaleru.SchoolResultreactive.repositories;

import com.faithfulolaleru.SchoolResultreactive.models.Score;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ScoreRepository extends ReactiveCrudRepository<Score, Integer> {

    Mono<Score> findByStudentIdAndTerm(Integer studentId, int term);

    Flux<Score> findAllByStudentId(Integer studentId);
}
