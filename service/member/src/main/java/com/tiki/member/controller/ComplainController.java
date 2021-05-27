package com.tiki.member.controller;


import com.tiki.member.domain.ComplainDTO;
import com.tiki.member.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComplainController {

    @Autowired
    private ComplainService service;


    @PostMapping("/comp")
    public int insertComplain(@RequestBody ComplainDTO complainDTO){
        return service.insertComplain(complainDTO);
    }

    @DeleteMapping("/comp/{idx}")
    public int deleteComplain(@PathVariable("idx") int idx){
        return service.deleteComplain(idx);
    }

    @GetMapping("/comp/spt/{spt}")
    public List<ComplainDTO> selectBySuspect(@PathVariable("spt") String spt){
        return service.selectListBySuspect(spt);
    }

    @GetMapping("/comp/repo/{repo}")
    public List<ComplainDTO> selectByReporter(@PathVariable("repo") String repo){
        return service.selectListByReporter(repo);
    }



}
