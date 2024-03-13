package com.kzvo.ptmbackend.service.impl;

import com.kzvo.ptmbackend.persistence.entity.user.RoleEntity;
import com.kzvo.ptmbackend.persistence.repository.RoleRepository;
import com.kzvo.ptmbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity getByName(String name) {
        return roleRepository.findRoleEntityByName(name);
    }
}
