package com.tiki.member.mapper;

import com.tiki.member.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public MemberDTO selectMemberDetail(String id);

    public int insertMember(MemberDTO memberDTO);

    public int deleteMember(String id);


}
