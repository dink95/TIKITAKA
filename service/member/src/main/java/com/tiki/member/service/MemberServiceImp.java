package com.tiki.member.service;

import com.tiki.member.domain.MemberDTO;
import com.tiki.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
