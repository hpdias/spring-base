package com.github.hpdias.base.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.hpdias.base.api.entity.Role;
import com.github.hpdias.base.api.enums.RoleNome;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNome(RoleNome roleName);
}