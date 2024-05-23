package com.godstimeProjects.librarySystem.repository;

import com.godstimeProjects.librarySystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
