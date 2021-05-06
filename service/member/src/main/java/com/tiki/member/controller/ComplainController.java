package com.tiki.member.controller;


import com.tiki.member.domain.ComplainDTO;
import com.tiki.member.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
