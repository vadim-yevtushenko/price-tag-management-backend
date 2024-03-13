package com.kzvo.ptmbackend.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private ZonedDateTime created;

    @Column(name = "updated", columnDefinition = "TIMESTAMP", nullable = false)
    private ZonedDateTime updated;

    @PrePersist
    public void onPrePersist() {
        ZonedDateTime now = ZonedDateTime.now();
        created = now;
        updated = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        updated = ZonedDateTime.now();
    }

}
