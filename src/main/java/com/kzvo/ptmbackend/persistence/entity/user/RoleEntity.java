package com.kzvo.ptmbackend.persistence.entity.user;

import com.kzvo.ptmbackend.persistence.entity.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

}
