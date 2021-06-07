package com.tiki.member.service;

import com.tiki.member.domain.ComplainDTO;
import com.tiki.member.mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainServiceImp implements ComplainService{


    @Autowired
    ComplainMapper mapper;

    @Override
    public int insertComplain(ComplainDTO complainDTO) {
        return mapper.insertComplain(complainDTO);
    }

    @Override
    public int deleteComplain(int compIdx) {
        return mapper.deleteComplain(compIdx);
    }

    @Override
    public List<ComplainDTO> selectListBySuspect(String spt) {
        return mapper.selectListBySuspect(spt);
    }

    @Override
    public List<ComplainDTO> selectListByReporter(String repo) {
        return mapper.selectListByReporter(repo);
    }

    @Override
    public List<ComplainDTO> selectAllComplains() {
        return mapper.selectAllComplains();
    }

    @Override
    public ComplainDTO selectComplainDetail(int compIdx) {
        return mapper.selectComplainDetail(compIdx);
    }

    @Override
    public List<ComplainDTO> selectListNotRead() {
        return mapper.selectListNotRead();
    }

    @Override
    public List<ComplainDTO> selectListAlreadyRead() {
        return mapper.selectListAlreadyRead();
    }
}
