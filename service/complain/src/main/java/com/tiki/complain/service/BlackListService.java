package com.tiki.complain.service;

import com.tiki.complain.domain.BlacklistDTO;

import java.util.List;

public interface BlackListService {

    int insertBlackList(String mbrId);

    List<BlacklistDTO> selectAllBlackList();


}
