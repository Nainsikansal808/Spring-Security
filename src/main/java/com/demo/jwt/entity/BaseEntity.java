package com.demo.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;
}
