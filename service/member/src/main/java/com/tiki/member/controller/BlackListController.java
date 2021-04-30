package com.tiki.member.controller;


import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mbr")
public class BlackListController {


    @PostMapping("/black")
    public int insertBlackList(String mbrId){
        return 0;
    }
}
