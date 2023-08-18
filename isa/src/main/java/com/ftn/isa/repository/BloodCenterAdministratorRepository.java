package com.ftn.isa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.model.BloodCenterAdministrator;

public interface BloodCenterAdministratorRepository extends JpaRepository<BloodCenterAdministrator, Long> {
    public Optional<BloodCenterAdministrator> findOneByUsername(String username);
}
