package com.tiki.member.controller;


import com.tiki.member.domain.BlacklistDTO;
import com.tiki.member.domain.MemberDTO;
import com.tiki.member.service.BlackListService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mbr")
public class BlackListController {


    @Autowired
    private BlackListService service;

    @PostMapping("/black")
    public int insertBlackList(@RequestBody MemberDTO memberDTO){

        return service.insertBlackList(memberDTO.getMbrId());
    }

    @GetMapping("/black")
    public List<BlacklistDTO> selectAllBlackList(){
        return service.selectAllBlackList();
    }
}
