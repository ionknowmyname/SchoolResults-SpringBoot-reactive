package com.faithfulolaleru.SchoolResultreactive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "studentClass is required")
    private String studentClass;
}
