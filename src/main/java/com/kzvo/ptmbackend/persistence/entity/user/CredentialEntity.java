package com.kzvo.ptmbackend.persistence.entity.user;

import com.kzvo.ptmbackend.persistence.entity.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
@NoArgsConstructor
public class CredentialEntity extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
