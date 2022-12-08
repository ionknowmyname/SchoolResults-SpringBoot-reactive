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
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
// @EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column("created_at")
//    @CreationTimestamp
    @CreatedDate
    private Instant createdAt;

    @Column("updated_at")
//    @UpdateTimestamp
    @LastModifiedDate
    private Instant updatedAt;


//    public Audit() {
//        this.createdAt = Instant.now();
//    }
}
