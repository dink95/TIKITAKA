package com.tiki.member.service;

import com.tiki.member.mapper.BlackListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackListServiceImp implements BlackListService{

    @Autowired
    BlackListMapper mapper;

    @Override
    public int insertBlackList(String mbrId) {
        return mapper.insertBlackList(mbrId);
    }
}
