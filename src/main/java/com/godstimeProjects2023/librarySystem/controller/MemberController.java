package com.godstimeProjects2023.librarySystem.controller;

import com.godstimeProjects2023.librarySystem.entity.Member;
import com.godstimeProjects2023.librarySystem.service.MemberService;
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
        Member newMember = memberService.addNewMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        if (!memberService.getMemberById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        member.setId(id);
        Member updatedMember = memberService.saveMember(member);
        return ResponseEntity.ok(updatedMember);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalMemberCount() {
        Long count = memberService.getTotalMemberCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/faculty-count")
    public ResponseEntity<Long> getFacultyMemberCount() {
        Long count = memberService.getFacultyMemberCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/students-count")
    public ResponseEntity<Long> getStudentsMemberCount() {
        Long count = memberService.getStudentsMemberCount();
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }
}

