package com.tiki.member.service;

import com.tiki.member.domain.BlacklistDTO;
import com.tiki.member.domain.MemberDTO;

import java.util.List;

public interface BlackListService {

    public int insertBlackList(String mbrId);

    public List<BlacklistDTO> selectAllBlackList();


}
