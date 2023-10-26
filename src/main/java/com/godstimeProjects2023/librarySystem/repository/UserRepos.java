package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepos extends JpaRepository<User, String> {
    List<User> findByFirstNameStartsWith(String name);
    User findByEmail(String email);
    User findByPhoneNumber(String phone);

    Optional<User> findByUsername(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
