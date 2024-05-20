package com.godstimeProjects2023.librarySystem.secureCH.repository;

import com.godstimeProjects2023.librarySystem.secureCH.entity.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SecuredUser, Long> {


    Optional<SecuredUser> findByUsername(String username);
}
