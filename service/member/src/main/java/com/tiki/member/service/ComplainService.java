package com.tiki.member.service;

import com.tiki.member.domain.ComplainDTO;

import java.util.List;

public interface ComplainService {

    int insertComplain(ComplainDTO complainDTO);

    int deleteComplain(int compIdx);

    List<ComplainDTO> selectListBySuspect(String spt);

    List<ComplainDTO> selectListByReporter(String repo);

    List<ComplainDTO> selectAllComplains();

    ComplainDTO selectComplainDetail(int compIdx);

    List<ComplainDTO> selectListNotRead();

    List<ComplainDTO> selectListAlreadyRead();
}
