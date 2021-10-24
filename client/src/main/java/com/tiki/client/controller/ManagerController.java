package com.tiki.client.controller;

import com.tiki.client.config.CryptAES256;
import com.tiki.client.domain.ManagerDTO;
import com.tiki.client.domain.MemberDTO;
import com.tiki.client.domain.paging.SearchDTO;
import com.tiki.client.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/login/manager/process") /*로그인*/
    @ResponseBody
    public Map<String, Object> loginManAccess(@RequestParam(value = "manId") String manId,
                                              @RequestParam(value = "manPwd") String manPwd,
                                              HttpServletResponse response) throws Exception {
        ModelAndView view = new ModelAndView();
        ManagerDTO managerDTO = new ManagerDTO();

        Map<String, Object> resultMap = new HashMap<>();

        managerDTO.setManId(manId);
        managerDTO.setManPwd(manPwd);

        String token = managerService.loginManager(managerDTO);

        String jwt = CryptAES256.decryptAES256(token,manId);
        Cookie cookie = new Cookie("token",jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        Cookie id = new Cookie("mbrId",manId);
        cookie.setPath("/");
        id.setPath("/");
        response.addCookie(id);
        response.addCookie(cookie);

        try {
            if (manId != null) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "OK");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "아이디 또는 비밀번호가 일치하지 않습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }

    @GetMapping(value = "/logout_manager") /*로그아웃*/
    public ModelAndView logoutManager(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();

        Cookie idCookie = WebUtils.getCookie(request, "manId");
        if(idCookie != null){ // 쿠키가 한개라도 있으면 실행
            idCookie.setMaxAge(0);
            response.addCookie(idCookie); // 응답 헤더에 추가
        }
        Cookie tokenCookie = WebUtils.getCookie(request, "token");
        if(tokenCookie != null){ // 쿠키가 한개라도 있으면 실행
            tokenCookie.setMaxAge(0);
            response.addCookie(tokenCookie); // 응답 헤더에 추가
        }
        view.setViewName("redirect:/");
        return view;
    }
}
