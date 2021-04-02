package com.tiki.member.mapper;

import com.tiki.member.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public MemberDTO selectMemberDetail(String id);

    public int insertMember(MemberDTO memberDTO);

    public int deleteMember(String id);

    public String findMemberId(MemberDTO dto);

    public String existPwd(MemberDTO dto);

    public int updateMember(MemberDTO dto);


}
