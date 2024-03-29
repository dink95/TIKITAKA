package com.tiki.complain.controller;

import com.tiki.complain.domain.ComplainDTO;
import com.tiki.complain.domain.paging.SearchDTO;
import com.tiki.complain.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComplainController {

    @Autowired
    private ComplainService service;


    @PostMapping("/comp/insert")
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

    @PostMapping("/comp/totalcount")
    public int getTotalCount(@RequestBody SearchDTO searchDTO){ return service.getTotalCount(searchDTO);}

    @PostMapping("/comp")
    public List<ComplainDTO> selectAllComplains(@RequestBody SearchDTO searchDTO){
        return service.selectAllComplains(searchDTO);
    }

    @GetMapping("/comp/{idx}")
    public ComplainDTO selectComplainDetailByIdx(@PathVariable("idx")int compIdx){
        service.updateReadCheck(compIdx); // 읽음표시
        return service.selectComplainDetail(compIdx);
    }

    @GetMapping("/comp/not")
    public List<ComplainDTO> selectListNotReadYet(){
        return service.selectListNotRead();
    }

    @GetMapping("/comp/already")
    public List<ComplainDTO> selectListAlreadyRead(){
        return service.selectListAlreadyRead();
    }



}