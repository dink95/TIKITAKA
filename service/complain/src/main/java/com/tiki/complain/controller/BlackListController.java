package com.tiki.complain.controller;

import com.tiki.complain.domain.BlacklistDTO;
import com.tiki.complain.domain.MemberDTO;
import com.tiki.complain.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
