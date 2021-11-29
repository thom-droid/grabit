package com.cabbage.grabit.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiverRepository extends JpaRepository<Giver, Long> {

    Optional<Giver> findByEmail(String email);
}
