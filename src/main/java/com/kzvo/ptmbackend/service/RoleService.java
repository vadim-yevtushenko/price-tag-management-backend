package com.kzvo.ptmbackend.service;

import com.kzvo.ptmbackend.persistence.entity.user.RoleEntity;

public interface RoleService {

    RoleEntity getByName(String name);

}
