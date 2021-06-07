package com.tiki.member.mapper;

import com.tiki.member.domain.ComplainDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplainMapper {

    int insertComplain(ComplainDTO complainDTO);

    int deleteComplain(int compIdx);

    List<ComplainDTO> selectListBySuspect(String spt);

    List<ComplainDTO> selectListByReporter(String repo);

    List<ComplainDTO> selectAllComplains();

    ComplainDTO selectComplainDetail(int compIdx);

    List<ComplainDTO> selectListNotRead();

    List<ComplainDTO> selectListAlreadyRead();

    int updateReadCheck(int compIdx);
}
