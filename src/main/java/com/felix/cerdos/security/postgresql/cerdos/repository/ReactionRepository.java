package com.felix.cerdos.security.postgresql.cerdos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felix.cerdos.security.postgresql.cerdos.models.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
