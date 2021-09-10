package com.tiki.complain.service;

import com.tiki.complain.domain.BlacklistDTO;
import com.tiki.complain.mapper.BlackListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListServiceImp implements BlackListService {

    @Autowired
    BlackListMapper mapper;

    @Override
    public int insertBlackList(String mbrId) {
        return mapper.insertBlackList(mbrId);
    }

    @Override
    public List<BlacklistDTO> selectAllBlackList() {
        return mapper.selectAllBlackList();
    }
}
