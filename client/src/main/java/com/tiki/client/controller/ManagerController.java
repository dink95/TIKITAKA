package com.tiki.client.controller;

import com.tiki.client.domain.ManagerDTO;
import com.tiki.client.domain.MemberDTO;
import com.tiki.client.domain.paging.SearchDTO;
import com.tiki.client.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
                                              HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        ManagerDTO managerDTO = new ManagerDTO();

        Map<String, Object> resultMap = new HashMap<>();

        managerDTO.setManId(manId);
        managerDTO.setManPwd(manPwd);

        String managerId = managerService.loginManager(managerDTO);

        try {
            if (managerId != null) {
                request.getSession().setAttribute("managerId", managerId);
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
    public ModelAndView logoutManager(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        if (request.getSession().getAttribute("managerId") != null) {
            request.getSession().removeAttribute("managerId");
        }
        view.setViewName("redirect:/");
        return view;
    }
}
