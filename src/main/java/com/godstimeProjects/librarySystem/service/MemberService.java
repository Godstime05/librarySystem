package com.godstimeProjects.librarySystem.service;

import com.godstimeProjects.librarySystem.entity.Member;
import com.godstimeProjects.librarySystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberById(Long id) {
        return Optional.of(memberRepository.getById(id));
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id).orElse(null);
    }



    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
