package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.Member;
import com.godstimeProjects2023.librarySystem.mapper.MemberRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepo {

    private final JdbcTemplate jdbcTemplate;

    public List<Member> findAllByOrderByFirstNameAscMiddleNameAsc() {
        String sql = "SELECT * FROM member ORDER BY first_name ASC, middle_name ASC";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    public Long countByType(String type) {
        String sql = "SELECT COUNT(*) FROM member WHERE type = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, type);
    }
}

