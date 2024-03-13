package com.kzvo.ptmbackend.service;

import com.kzvo.ptmbackend.persistence.entity.user.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getById(Long id);

    void save(UserEntity userEntity);

    void deleteById(Long id);

}
