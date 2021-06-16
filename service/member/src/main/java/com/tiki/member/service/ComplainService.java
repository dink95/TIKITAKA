package com.tiki.member.service;

import com.tiki.member.domain.ComplainDTO;
import com.tiki.member.domain.paging.SearchDTO;

import java.util.List;

public interface ComplainService {

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
