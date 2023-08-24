package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.Member;
import com.godstimeProjects2023.librarySystem.mapper.MemberRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public List<Member> getAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    public Member addNew(Member member) {
        String insertSql = "INSERT INTO member(type,first_name, middle_name,last_name,gender,date_of_birth,joining_date,contact,email) VALUES (?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getType());
            ps.setString(2, member.getFirstName());
            ps.setString(3, member.getMiddleName());
            ps.setString(4, member.getLastName());
            ps.setString(5, member.getGender());
            ps.setDate(6, (Date) member.getDateOfBirth());
            ps.setDate(7, (Date) member.getJoiningDate());
            ps.setString(8, member.getContact());
            ps.setString(9, member.getEmail());

            return ps;
        }, keyHolder);
        member.setId((Long) keyHolder.getKeys().get("id"));
        return member;
    }

    public Member save(Member member) {
        String updateSql = "UPDATE member SET first_name = ?, middle_name = ?, last_name=?,gender=?,date_of_birth=?,joining_date=?,contact=?,email=? WHERE id = ?";
        jdbcTemplate.update(updateSql, member.getFirstName(), member.getMiddleName(), member.getLastName(),member.getGender(),
                member.getDateOfBirth(),member.getJoiningDate(), member.getContact(), member.getEmail(), member.getId());
        return member;
    }

    public Optional<Member> getMember(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        List<Member> members = jdbcTemplate.query(sql, new Object[]{id}, new MemberRowMapper());
        return members.isEmpty() ? Optional.empty() : Optional.of(members.get(0));
    }

    public Long getTotalCount() {
        String sql = "SELECT COUNT(*) FROM member";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long getFacultyCount() {
        String sql = "SELECT COUNT(*) FROM member WHERE type = 'Faculty'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long getStudentsCount() {
        String sql = "SELECT COUNT(*) FROM member WHERE type = 'Student'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public void deleteMember(Long id) {
        String deleteSql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
}
