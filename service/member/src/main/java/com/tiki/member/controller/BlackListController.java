package com.tiki.member.controller;


import com.tiki.member.domain.MemberDTO;
import com.tiki.member.service.BlackListService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackListController {


    @Autowired
    private BlackListService service;

    @PostMapping("/black")
    public int insertBlackList(@RequestBody MemberDTO memberDTO){

        return service.insertBlackList(memberDTO.getMbrId());
    }
}
