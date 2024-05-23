package com.godstimeProjects.librarySystem.controller;

import com.godstimeProjects.librarySystem.entity.Member;
import com.godstimeProjects.librarySystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @PostMapping("/add")
    public ResponseEntity<Member> addNewMember(@RequestBody Member member) {
        Member newMember = memberService.saveMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member existingMember = memberService.findMemberById(id);

        existingMember.setFirstName(member.getFirstName());
        existingMember.setLastName(member.getLastName());
        existingMember.setEmail(member.getEmail());
        existingMember.setContact(member.getContact());
        existingMember.setGender(member.getGender());
        existingMember.setDateOfBirth(member.getDateOfBirth());
        existingMember.setJoiningDate(member.getJoiningDate());
        existingMember.setMiddleName(member.getMiddleName());

        return memberService.saveMember(existingMember);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);

    }


}

