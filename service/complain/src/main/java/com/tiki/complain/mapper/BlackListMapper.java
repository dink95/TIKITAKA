package com.tiki.complain.mapper;

import com.tiki.complain.domain.BlacklistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackListMapper {

    int insertBlackList(String mbrId);

    List<BlacklistDTO> selectAllBlackList();

}
