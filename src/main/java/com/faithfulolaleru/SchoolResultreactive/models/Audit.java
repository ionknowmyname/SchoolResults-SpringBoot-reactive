package com.faithfulolaleru.SchoolResultreactive.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@MappedSuperclass
// @EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Audit {

    @Id
    private String id;


//    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();


//    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updatedAt;


//    public Audit() {
//        this.createdAt = LocalDateTime.now();
//    }
}
