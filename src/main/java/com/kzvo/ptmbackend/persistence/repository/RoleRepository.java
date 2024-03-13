package com.kzvo.ptmbackend.persistence.repository;

import com.kzvo.ptmbackend.persistence.entity.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleEntityByName(String name);

}
