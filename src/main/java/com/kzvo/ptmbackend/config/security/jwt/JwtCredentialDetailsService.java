package com.kzvo.ptmbackend.config.security.jwt;

import com.kzvo.ptmbackend.exceptions.notfound.NotFoundException;
import com.kzvo.ptmbackend.persistence.entity.user.CredentialEntity;
import com.kzvo.ptmbackend.persistence.entity.user.RoleEntity;
import com.kzvo.ptmbackend.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtCredentialDetailsService implements UserDetailsService {

    @Lazy
    @Autowired
    private CredentialService credentialService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CredentialEntity credentialEntity = credentialService.getByEmail(username);

        if (credentialEntity == null) {
            throw new NotFoundException("User with email: " + username + " not found.");
        }

        return new User(credentialEntity.getEmail(), credentialEntity.getPassword(),
                mapToGrantedAuthorities(credentialEntity.getUser().getRoles()));
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roles) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
