package com.tiki.member.service;

import com.tiki.member.domain.ManagerDTO;

public interface ManagerService {


    /*  계정정보 API    */
    public ManagerDTO selectManagerDetail(String manId);

    /*   비밀번호 수정     */
    public int updateManagerPassword(ManagerDTO managerDTO);

}
