package com.godstimeProjects.librarySystem.repository;

import com.godstimeProjects.librarySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
