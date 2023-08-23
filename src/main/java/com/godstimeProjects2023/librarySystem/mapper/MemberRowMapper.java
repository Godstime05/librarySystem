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
        member.setFirstName(rs.getString("first_name"));
        member.setMiddleName(rs.getString("middle_name"));


        // Set other properties here
        return member;
    }
}
