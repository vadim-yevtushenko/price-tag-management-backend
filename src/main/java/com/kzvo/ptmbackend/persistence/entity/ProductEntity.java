package com.kzvo.ptmbackend.persistence.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "provider")
    private String provider;

    @Column(name = "price")
    private double price;

}
