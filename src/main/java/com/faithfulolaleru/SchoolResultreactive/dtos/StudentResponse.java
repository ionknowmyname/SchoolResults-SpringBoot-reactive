package com.faithfulolaleru.SchoolResultreactive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Integer id;
    private String name;
    private String studentClass;
    private Instant createdAt;

}
