package com.kzvo.ptmbackend.service;

import com.kzvo.ptmbackend.persistence.entity.user.CredentialEntity;
import com.kzvo.ptmbackend.persistence.entity.user.UserEntity;
import org.springframework.http.ResponseEntity;

public interface CredentialService {

    ResponseEntity login(String email, String password);

    CredentialEntity getByEmail(String email);

    ResponseEntity userRegistration(String email, String password, UserEntity userEntity);

    ResponseEntity adminRegistration(String email, String password, UserEntity userEntity);

    void checkCredential(CredentialEntity credential, String password);

}
