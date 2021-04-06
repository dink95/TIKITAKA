package com.tiki.client.controller;
import com.tiki.client.domain.MemberDTO;
import com.tiki.client.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping(value = "/login") /*로그인 페이지*/
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/login");
        return view;
    }


    @PostMapping("/login/process") /*로그인*/
    @ResponseBody
    public Map<String, Object> loginAccess(@RequestParam(value = "userId") String userId,
                                           @RequestParam(value = "userPw") String userPw,
                                           @RequestParam(value = "userRole") Boolean userRole,
                                           HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();



        memberDTO.setMbrId(userId);
        memberDTO.setMbrPwd(userPw);
        memberDTO.setMbrRole(userRole);


        Boolean role =memberDTO.isMbrRole();


        String mbrId = memberService.login(memberDTO);

        try {
            if (mbrId != null) {
                request.getSession().setAttribute("mbrId", mbrId);
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

    @GetMapping(value = "/logout") /*로그아웃*/
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        if (request.getSession().getAttribute("mbrId") != null) {
            request.getSession().removeAttribute("mbrId");
        }
        view.setViewName("redirect:/");
        return view;
    }


    @GetMapping(value = "/signup") /*회원가입 페이지*/
    public ModelAndView signup() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/signup");
        return view;
    }


    @PostMapping("/login/join")  /*회원가입*/
    @ResponseBody
    public Map<String, Object> joinMember(@RequestBody MemberDTO memberDTO) {

        Map<String, Object> resultMap = new HashMap<>();

        int result = 0;

        try {
            result = memberService.createMember(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "회원가입을 완료하였습니다.");
            } else {
                resultMap.put("resultCode", 500);
                resultMap.put("resultMsg", "회원가입이 실패하였습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "회원가입이 실패하였습니다.");
        }


        return resultMap;
    }

    @RequestMapping("signup/idcheck") /*아이디 중복체크*/
    @ResponseBody
    public Map<String, Object> loginAccess(@RequestParam(value = "userId") String userId) {
        ModelAndView view = new ModelAndView();

        Map<String, Object> resultMap = new HashMap<>();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMbrId(userId);

        String mbrId  =  memberService.idcheck(memberDTO);

        try {
            if (mbrId == null) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "OK");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "이미 사용중인 아이디입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }

    @GetMapping(value = "/findid") /*아이디찾기 페이지*/
    public ModelAndView findid() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/findid");
        return view;
    }

    @RequestMapping("/login/findid") /*아이디찾기*/
    @ResponseBody
    public Map<String, Object> loginFindid(@RequestParam(value = "userNm") String userName,
                                           @RequestParam(value = "userPhone") String userPhone,
                                           HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();

        memberDTO.setMbrNm(userName);
        memberDTO.setMbrPhone(userPhone);

        String mbrId = memberService.findid(memberDTO);

        try {
            if (mbrId != null) {
                request.getSession().setAttribute("mbrId", mbrId);
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "회원님의 아이디는" + "" + mbrId + "입니다.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "가입된 아이디가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }

    @GetMapping(value = "/findpwd")  /*비밀번호찾기 페이지*/
    public ModelAndView findpwd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/findpwd");
        return view;
    }

    @RequestMapping("/login/findpwd") /*비밀번호 찾기*/
    @ResponseBody
    public Map<String, Object> loginFindpwd(@RequestParam(value = "userId") String userId,
                                            @RequestParam(value = "userName") String userName,
                                            @RequestParam(value = "userPhone") String userPhone,
                                            HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        Map<String, Object> resultMap = new HashMap<>();

        Boolean exist = false;

        try {
            exist= memberService.findpwd(userId, userName, userPhone);
            if (exist) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "ok");
                System.out.println("ok");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "no");
                System.out.println("no");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }


    @GetMapping(value = "/updatepwd")  /*비밀번호변경 페이지*/
    public ModelAndView updatepwd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/updatepwd");
        return view;
    }

    @RequestMapping("/login/update")  /*비밀번호 재설정*/
    @ResponseBody
    public Map<String, Object> updatePwd(@RequestParam(value = "userId") String userId,
                                         @RequestParam(value = "userPwd") String userPwd) {

        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();

        memberDTO.setMbrId(userId);
        memberDTO.setMbrPwd(userPwd);

        int result =0;

        try {

            result= memberService.update(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "비밀번호가 변경되었습니다. 다시 로그인해주세요.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "비밀번호 변경이 실패하였습니다. 관리자에게 문의하세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "관리자에게 문의하세요");
        }

        return resultMap;
    }


}