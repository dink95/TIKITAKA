package com.tiki.member.controller;

import com.tiki.member.domain.MemberDTO;
import com.tiki.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/mbr/{id}")
    public MemberDTO selectMemberById(@PathVariable("id") String id){
        return memberService.selectMemberDetail(id);
    }

    @PostMapping("/mbr")
    public int insertMember(@RequestBody MemberDTO memberDTO){
        MemberDTO dto = memberDTO;
        dto.setMbrPwd(passwordEncoder.encode(dto.getMbrPwd()));

        return memberService.insertMember(dto);
    }

    @DeleteMapping("/mbr/{id}")
    public int deleteMember(@PathVariable("id") String id){
        return memberService.deleteMember(id);
    }

    @PostMapping("/mbr/login")
    public String loginChecking(@RequestBody MemberDTO memberDTO) {
        MemberDTO dto = memberService.selectMemberDetail(memberDTO.getMbrId());
        if (dto != null || passwordEncoder.matches( memberDTO.getMbrPwd(),dto.getMbrPwd())) {
            return dto.getMbrId();
        } else {
            return null;
        }
    }
}
