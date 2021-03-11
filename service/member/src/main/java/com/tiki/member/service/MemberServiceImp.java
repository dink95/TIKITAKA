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
    public int insertMember(MemberDTO memberDTO) {
        return mapper.insertMember(memberDTO);
    }

    @Override
    public int deleteMember(String id) {
        return mapper.deleteMember(id);
    }
}
