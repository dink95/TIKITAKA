package com.tiki.member.service;

import com.tiki.member.domain.MemberDTO;

public interface MemberService {

    public MemberDTO selectMemberDetail(String id);

    public int insertMember(MemberDTO memberDTO);

    public int deleteMember(String id);


}
