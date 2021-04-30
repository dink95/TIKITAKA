package com.tiki.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlackListMapper {

    public int insertBlackList(String mbrId);

}
