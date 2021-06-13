package com.tiki.member.controller;


import com.tiki.member.domain.ManagerDTO;
import com.tiki.member.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerController {

    @Autowired
    ManagerService service;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/man/{manId}")
    public ManagerDTO selectManagerDetail(@PathVariable("manId")String manId){
        return selectManagerDetail(manId);
    }

    @PostMapping("/man/login")
    public String managerLogin(@RequestBody ManagerDTO managerDTO){
        ManagerDTO dto = service.selectManagerDetail(managerDTO.getManId());

        if(dto!=null && managerDTO.getManPwd().equals(dto.getManPwd())){
            return dto.getManId();
        }
        else
        {
            return null;
        }
    }
    @PatchMapping("/man")
    public int updateManagerPassword(@RequestBody ManagerDTO managerDTO){
        return service.updateManagerPassword(managerDTO);
    }



}
