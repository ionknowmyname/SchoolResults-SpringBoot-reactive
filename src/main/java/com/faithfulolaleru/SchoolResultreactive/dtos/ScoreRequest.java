package com.faithfulolaleru.SchoolResultreactive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequest {

    @NotBlank(message = "studentId is required")
    private Integer studentId;

    @NotBlank(message = "term is required")
    private int term;

    @NotBlank(message = "subject1Score is required")
    private Integer subject1Score;

    @NotBlank(message = "subject2Score is required")
    private Integer subject2Score;

    @NotBlank(message = "subject3Score is required")
    private Integer subject3Score;

    @NotBlank(message = "subject4Score is required")
    private Integer subject4Score;

    private Integer subject5Score;

    private Integer subject6Score;
}
