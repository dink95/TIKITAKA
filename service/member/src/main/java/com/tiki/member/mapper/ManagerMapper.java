package com.tiki.member.mapper;

import com.tiki.member.domain.ManagerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerMapper {

    /*  계정정보 API    */
    public ManagerDTO selectManagerDetail(String manId);

    /*   비밀번호 수정     */
    public int updateManagerPassword(ManagerDTO managerDTO);
}
