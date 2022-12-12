package com.faithfulolaleru.SchoolResultreactive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreResponse {

    private Integer id;
    private Integer studentId;
    private int term;
    private Integer subject1Score;
    private Integer subject2Score;
    private Integer subject3Score;
    private Integer subject4Score;
    private Integer subject5Score;
    private Integer subject6Score;
    private Instant createdAt;
    private String message;
}
