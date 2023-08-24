package com.godstimeProjects2023.librarySystem.mapper;

import com.godstimeProjects2023.librarySystem.entity.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setType(rs.getString("type"));
        member.setFirstName(rs.getString("first_name"));
        member.setMiddleName(rs.getString("middle_name"));
        member.setLastName(rs.getString("last_name"));
        member.setGender(rs.getString("gender"));
        member.setDateOfBirth(rs.getDate("date_of_birth"));
        member.setJoiningDate(rs.getDate("joining_date"));
        member.setContact(rs.getString("contact"));
        member.setEmail(rs.getString("email"));

        return member;
    }
}
