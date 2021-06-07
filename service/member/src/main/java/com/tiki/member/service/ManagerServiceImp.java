package com.tiki.member.service;

import com.tiki.member.domain.ManagerDTO;
import com.tiki.member.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImp implements ManagerService{

    @Autowired
    ManagerMapper mapper;

    @Override
    public ManagerDTO selectManagerDetail(String manId) {
        return mapper.selectManagerDetail(manId);
    }

    @Override
    public int updateManagerPassword(ManagerDTO managerDTO) {
        return mapper.updateManagerPassword(managerDTO);
    }
}
