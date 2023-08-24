package com.godstimeProjects2023.librarySystem.service;

import com.godstimeProjects2023.librarySystem.constants.Constants;
import com.godstimeProjects2023.librarySystem.entity.Member;
import com.godstimeProjects2023.librarySystem.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.getAll();
    }

    public Member addNewMember(Member member) {
        member.setJoiningDate( new Date() );
        return memberRepository.addNew(member);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.getMember(id);
    }

    public Long getTotalMemberCount() {
        return memberRepository.getTotalCount();
    }

    public Long getFacultyMemberCount() {
        return memberRepository.getFacultyCount();
    }

    public Long getFacultyCount() {
        return memberRepository.countByType(Constants.MEMBER_FACULTY);
    }

    public Long getStudentsMemberCount() {
        return memberRepository.getStudentsCount();
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteMember(id);
    }
}
