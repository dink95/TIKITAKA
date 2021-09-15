package com.tiki.member.controller;


import com.tiki.member.domain.ManagerDTO;
import com.tiki.member.service.ManagerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class ManagerController {

    @Autowired
    ManagerService service;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/man/Auth/{manId}")
    public ManagerDTO selectManagerDetail(@PathVariable("manId")String manId){
        return selectManagerDetail(manId);
    }

    @PostMapping("/man/unAuth/login")
    public String managerLogin(@RequestBody ManagerDTO managerDTO, HttpServletResponse response){
        ManagerDTO dto = service.selectManagerDetail(managerDTO.getManId());

        if(dto!=null && managerDTO.getManPwd().equals(dto.getManPwd())){

            String manId = managerDTO.getManId();

            String token = Jwts.builder()
                    .setSubject(manId)
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SignatureAlgorithm.HS512,"tiki")
                    .compact();

            response.addHeader("token", token);
            response.addHeader("userId",manId);


            return dto.getManId();

        }
        else
        {
            return null;
        }
    }
    @PatchMapping("/man/Auth")
    public int updateManagerPassword(@RequestBody ManagerDTO managerDTO){
        return service.updateManagerPassword(managerDTO);
    }



}
