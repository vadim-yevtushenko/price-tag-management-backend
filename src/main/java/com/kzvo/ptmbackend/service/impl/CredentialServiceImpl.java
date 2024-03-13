package com.kzvo.ptmbackend.service.impl;

import com.kzvo.ptmbackend.config.security.jwt.JwtProvider;
import com.kzvo.ptmbackend.mapper.UserMapper;
import com.kzvo.ptmbackend.persistence.entity.user.CredentialEntity;
import com.kzvo.ptmbackend.persistence.entity.user.UserEntity;
import com.kzvo.ptmbackend.persistence.repository.CredentialRepository;
import com.kzvo.ptmbackend.persistence.repository.RoleRepository;
import com.kzvo.ptmbackend.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    private final String ROLE_ADMIN = "ROLE_ADMIN";
    private final String ROLE_SELLER = "ROLE_SELLER";

    @Override
    public ResponseEntity login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            CredentialEntity credentialEntity = credentialRepository.findCredentialEntityByEmail(email);

            return ResponseEntity.ok(createResponse(email, password, credentialEntity));
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @Override
    public CredentialEntity getByEmail(String email) {
        return credentialRepository.findCredentialEntityByEmail(email);
    }

    @Override
    public ResponseEntity userRegistration(String email, String password, UserEntity userEntity) {
        userEntity.setRoles(List.of(roleRepository.findRoleEntityByName(ROLE_SELLER)));
        return registration(email, password, userEntity);
    }

    @Override
    public ResponseEntity adminRegistration(String email, String password, UserEntity userEntity) {
        userEntity.setRoles(List.of(roleRepository.findRoleEntityByName(ROLE_ADMIN)));
        return registration(email, password, userEntity);
    }

    private ResponseEntity registration(String email, String password, UserEntity userEntity){
        if (isUsedEmail(email)){
            throw new BadCredentialsException(String.format("User with address %s already exist.", email));
        }

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setEmail(email);
        credentialEntity.setPassword(passwordEncoder.encode(password));
        credentialEntity.setUser(userEntity);
        credentialEntity = credentialRepository.save(credentialEntity);

        return ResponseEntity.ok(createResponse(email, password, credentialEntity));
    }

    private Map<String, Object> createResponse(String email, String password, CredentialEntity credentialEntity){
        String token = jwtProvider.createToken(email, password, credentialEntity.getUser().getRoles());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", userMapper.mapEntityToDto(credentialEntity.getUser()));

        return response;
    }

    @Override
    public void checkCredential(CredentialEntity credential, String password) {
        if (credential != null) {
            if (!passwordEncoder.matches(password, credential.getPassword())) {
                throw new BadCredentialsException("Current password is wrong.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("No credentials found to use.");
        }
    }

    private boolean isUsedEmail(String email) {
        CredentialEntity credentialEntity = getByEmail(email);
        return credentialEntity != null;
    }

}
