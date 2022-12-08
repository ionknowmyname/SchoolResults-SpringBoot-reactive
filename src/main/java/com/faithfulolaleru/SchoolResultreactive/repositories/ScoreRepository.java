package com.faithfulolaleru.SchoolResultreactive.repositories;

import com.faithfulolaleru.schoolResult.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    Optional<Score> findByStudentIdAndTerm(Integer studentId, int term);

    List<Score> findAllByStudentId(Integer studentId);
}
