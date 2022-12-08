package com.faithfulolaleru.SchoolResultreactive.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//import javax.persistence.Column;
//import javax.persistence.Entity;
import java.io.Serializable;

@Table(value = "scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score extends Audit implements Serializable {

    @Column("student_id")
    private Integer studentId;

    @Column("term")
    private int term;

    @Column("subject1_score")
    private Integer subject1Score;

    @Column("subject2_score")
    private Integer subject2Score;

    @Column("subject3_score")
    private Integer subject3Score;

    @Column("subject4_score")
    private Integer subject4Score;

    @Column("subject5_score")
    private Integer subject5Score;

    @Column("subject6_score")
    private Integer subject6Score;
}
