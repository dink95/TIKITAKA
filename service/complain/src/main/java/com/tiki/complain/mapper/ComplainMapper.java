package com.tiki.complain.mapper;


import com.tiki.complain.domain.ComplainDTO;
import com.tiki.complain.domain.paging.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplainMapper {

    int getTotalCount(SearchDTO searchDTO);

    int insertComplain(ComplainDTO complainDTO);

    int deleteComplain(int compIdx);

    List<ComplainDTO> selectListBySuspect(String spt);

    List<ComplainDTO> selectListByReporter(String repo);

    List<ComplainDTO> selectAllComplains(SearchDTO searchDTO);

    ComplainDTO selectComplainDetail(int compIdx);

    List<ComplainDTO> selectListNotRead();

    List<ComplainDTO> selectListAlreadyRead();

    int updateReadCheck(int compIdx);
}
