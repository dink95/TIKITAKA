package com.tiki.member.controller;

import com.tiki.member.domain.MemberDTO;
import com.tiki.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Slf4j
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
        if (dto != null && passwordEncoder.matches( memberDTO.getMbrPwd(),dto.getMbrPwd())) {
            return dto.getMbrId();
        } else {
            return null;
        }
    }

    @PostMapping("/mbr/idcheck")
    public String idChecking(@RequestBody MemberDTO memberDTO) {
        MemberDTO dto = memberService.selectMemberDetail(memberDTO.getMbrId());
        if (dto != null) {
            return dto.getMbrId();
        } else {
            return null;
        }
    }



    @GetMapping("/mbr/{id}/{name}/{phone}")
    public Boolean memberPasswordChecking(@PathVariable("id") String id,
                                          @PathVariable("name") String name,
                                          @PathVariable("phone") String phone){
        MemberDTO dto = new MemberDTO(name, id, phone);
        String exists = memberService.existPwd(dto);

        System.out.println(id +":"+name+":"+phone);

        if(exists==null){
            return false;
        }
        else {
            return true;
        }

    }


    @PostMapping("/mbr/id")
    public String findMemberId(@RequestBody MemberDTO memberDTO){
        return  memberService.findMemberId(memberDTO);
    }


    @PatchMapping("/mbr/password")
    public int updatePassword(@RequestBody MemberDTO memberDTO){
        memberDTO.setMbrPwd(passwordEncoder.encode(memberDTO.getMbrPwd()));
        return memberService.updateMember(memberDTO);
    }





}
