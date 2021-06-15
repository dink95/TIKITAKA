package com.tiki.member.mapper;

import com.tiki.member.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public MemberDTO selectMemberDetail(String id);

    public String existId(String id);

    public String existPhone(String phone);

    public String existEmail(String email);

    public String existPwd(MemberDTO dto);

    public int insertMember(MemberDTO memberDTO);

    public int deleteMember(String id);

    public String findMemberId(MemberDTO dto);

    public int updateMemberPwd(MemberDTO dto);

    public int updateRole(MemberDTO dto);

    public int updateMemberInfo(MemberDTO dto);

    public int updateMemberPoints(MemberDTO memberDTO);

    public int updateMemberGrade(MemberDTO dto);
}
