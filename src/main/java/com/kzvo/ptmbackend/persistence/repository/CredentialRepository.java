package com.kzvo.ptmbackend.persistence.repository;

import com.kzvo.ptmbackend.persistence.entity.user.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    CredentialEntity findCredentialEntityByEmail(String email);

}
