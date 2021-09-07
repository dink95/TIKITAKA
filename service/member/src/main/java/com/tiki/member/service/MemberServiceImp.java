package com.tiki.member.service;

import com.tiki.member.domain.MemberDTO;
import com.tiki.member.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    private MemberMapper mapper;

    @Override
    public MemberDTO selectMemberDetail(String id) {
        return mapper.selectMemberDetail(id);
    }

    @Override
    public String existId(String id) {
        return mapper.existId(id);
    }

    @Override
    public String existPhone(String phone) {
        return mapper.existPhone(phone);
    }

    @Override
    public String existEmail(String email) {
        return mapper.existEmail(email);
    }

    @Override
    public int insertMember(MemberDTO memberDTO) {
        return mapper.insertMember(memberDTO);
    }

    @Override
    public int deleteMember(String id) {
        return mapper.deleteMember(id);
    }

    @Override
    public String findMemberId(MemberDTO dto) {
        return mapper.findMemberId(dto);
    }

    @Override
    public String existPwd(MemberDTO dto) {
        return mapper.existPwd(dto);
    }

    @Override
    public int updateMemberPwd(MemberDTO dto) {
        return mapper.updateMemberPwd(dto);
    }

    @Override
    public int updateRole(MemberDTO dto) { return mapper.updateRole(dto); }

    @Override
    public int updateMemberInfo(MemberDTO dto) {
        return mapper.updateMemberInfo(dto);
    }

    @Override
    public int updateMemberPoints(MemberDTO memberDTO) {
        return mapper.updateMemberPoints(memberDTO);
    }

    @Override
    public int updateMemberGrade(MemberDTO dto) {
        return mapper.updateMemberGrade(dto);
    }


    //springSecurity 로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        log.debug("{}",username);
         MemberDTO memberDTO = mapper.selectMemberDetail(username);
         if(memberDTO==null)
             throw new UsernameNotFoundException(username);


         return new User(memberDTO.getMbrId(),memberDTO.getMbrPwd(),true,true,true,true,
                 new ArrayList<>());
    }
}
