package com.tiki.member.mapper;

import com.tiki.member.domain.BlacklistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackListMapper {

    public int insertBlackList(String mbrId);

    public List<BlacklistDTO> selectAllBlackList();

}
