package com.tiki.member.service;

import com.tiki.member.domain.MemberDTO;

public interface MemberService {

    public MemberDTO selectMemberDetail(String id);

    public String existId(String id);

    public String existPhone(String phone);

    public String existEmail(String email);

    public int insertMember(MemberDTO memberDTO);

    public int deleteMember(String id);

    public String findMemberId(MemberDTO dto);

    public String existPwd(MemberDTO dto);

    public int updateMember(MemberDTO dto);


}
