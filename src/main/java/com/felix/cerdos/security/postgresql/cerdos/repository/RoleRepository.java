package com.felix.cerdos.security.postgresql.cerdos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felix.cerdos.security.postgresql.cerdos.models.ERole;
import com.felix.cerdos.security.postgresql.cerdos.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}