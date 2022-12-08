package com.faithfulolaleru.SchoolResultreactive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//import javax.persistence.Column;
//import javax.persistence.Entity;
import java.io.Serializable;

//@Entity(name = "students")
@Table(value = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends Audit implements Serializable {

    @Column("name")
    private String name;

    @Column("student_class")
    private String studentClass;
}
