package com.faithfulolaleru.SchoolResultreactive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "scores-reactive")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Score implements Serializable {

    @Id
    private String id;

    private String studentId;

    private int term;

    private Integer subject1Score;

    private Integer subject2Score;

    private Integer subject3Score;

    private Integer subject4Score;

    private Integer subject5Score;

    private Integer subject6Score;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
