package com.tiki.client.controller;

import com.tiki.client.config.CryptAES256;
import com.tiki.client.domain.MemberDTO;
import com.tiki.client.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/")
    public String Home() {
        return "/index";
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
                                           HttpServletResponse response) throws Exception {
        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);
        memberDTO.setMbrPwd(userPw);


        String token = memberService.login(memberDTO);

        if(token==null){


        }else{
            String mbrId= userId;
            String jwt = CryptAES256.decryptAES256(token,mbrId);
            Cookie cookie = new Cookie("token",jwt);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            Cookie id = new Cookie("mbrId",mbrId);
            response.addCookie(id);
            response.addCookie(cookie);
//            response.addHeader("token",jwt);
        }

//        try {
//            if (mbrId != null) {
//
//                resultMap.put("resultCode", 200);
//                resultMap.put("resultMsg", "OK");
//            } else {
//                resultMap.put("resultCode", 400);
//                resultMap.put("resultMsg", "아이디 또는 비밀번호가 일치하지 않습니다");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultMap.put("resultCode", 500);
//            resultMap.put("resultMsg", e.getMessage());
//        }
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

    @RequestMapping("/member/detail") /*멤버 정보 조회*/
    @ResponseBody
    public Map<String, Object> memberDetail(@RequestParam(value = "userId") String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        MemberDTO memberDTO = memberService.Detail(userId);
        try {
            resultMap.put("memberDetail", memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("detail error");
        }
        return resultMap;
    }

    @GetMapping(value = "/signup") /*회원가입 페이지*/
    public ModelAndView signup() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/signup");
        return view;
    }


    @RequestMapping("/login/join")  /*회원가입*/
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

    @RequestMapping("/login/idcheck") /*아이디 중복체크*/
    @ResponseBody
    public Map<String, Object> loginIdCheck(@RequestParam(value = "userId") String id) {
        Map<String, Object> resultMap = new HashMap<>();

        Boolean exist = false;
        try {
            exist = memberService.existId(id);
            if (!exist) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "* 사용 가능한 아이디입니다.");
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

    @RequestMapping("/login/phonecheck") /*전화번호 중복체크*/
    @ResponseBody
    public Map<String, Object> loginPhoneCheck(@RequestParam(value = "userPhone") String phone) {
        Map<String, Object> resultMap = new HashMap<>();

        Boolean exist = false;
        try {
            exist = memberService.existPhone(phone);
            if (!exist) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "* 사용 가능한 전화번호입니다.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "이미 사용중인 전화번호입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/login/emailcheck") /*이메일 중복체크*/
    @ResponseBody
    public Map<String, Object> loginEmailCheck(@RequestParam(value = "userEmail") String email) {
        Map<String, Object> resultMap = new HashMap<>();

        Boolean exist = false;
        try {
            exist = memberService.existEmail(email);
            if (!exist) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "* 사용 가능한 이메일입니다.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "이미 사용중인 이메일입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 503);
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
                                           @RequestParam(value = "userPhone") String userPhone) {
        ModelAndView view = new ModelAndView();
        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();

        memberDTO.setMbrNm(userName);
        memberDTO.setMbrPhone(userPhone);

        String mbrId = memberService.findId(memberDTO);

        try {
            if (mbrId != null) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", mbrId);
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


    @RequestMapping(value = "/findpwd")  /*비밀번호찾기 페이지*/
    public String findpwd(Model model, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        String result = null;
        try {
            String referer = request.getHeader("REFERER");
            if (referer != null) {
                result = "/member/findpwd";
            } else {
                result = "/index";
                model.addAttribute("modelCode", 400);
                model.addAttribute("modelMsg", "올바른 접근이 아닙니다.");
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }


    @RequestMapping("/login/findpwd") /*비밀번호 찾기*/
    @ResponseBody
    public Map<String, Object> loginFindpwd(@RequestParam(value = "userId") String userId,
                                            @RequestParam(value = "userName") String userName,
                                            @RequestParam(value = "userPhone") String userPhone,
                                            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();

        Boolean exist = false;

        try {
            exist = memberService.findPwd(userId, userName, userPhone);
            if (exist) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "ok");
                request.getSession().setAttribute("userId", userId);
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "정보가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }


    @RequestMapping(value = "/updatepwd")  /*비밀번호변경 페이지*/
    public String updatepwd(Model model, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        String result = null;
        try {
            String referer = request.getHeader("REFERER");
            if (referer != null) {
                result = "/member/updatepwd";
            } else {
                result = "/index";
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    @RequestMapping("/login/update")  /*비밀번호 재설정*/
    @ResponseBody
    public Map<String, Object> updatePwd(@RequestParam(value = "userId") String userId,
                                         @RequestParam(value = "userPwd") String userPwd) {

        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);
        memberDTO.setMbrPwd(userPwd);

        int result = 0;

        try {

            result = memberService.updatePwd(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "비밀번호가 변경되었습니다.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "비밀번호 변경이 실패하였습니다. 관리자에게 문의하세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "실패하였습니다. 관리자에게 문의하세요");
        }

        return resultMap;
    }

    @GetMapping(value = "/member/myinfo") /*내정보 페이지*/
    public ModelAndView myinfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/myinfo");
        return view;
    }

    @GetMapping(value = "/member/userinfo") /*유저정보 페이지*/
    public ModelAndView userinfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/userinfo");
        return view;
    }

    @RequestMapping(value = "/member/info/update")  /*개인정보수정 페이지*/
    public ModelAndView updateinfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/update");
        return view;
    }


    @RequestMapping("/info/update")  /*개인정보 수정*/
    @ResponseBody
    public Map<String, Object> updatePwd(@RequestParam(value = "userId") String userId,
                                         @RequestParam(value = "userAddr") String userAddr,
                                         @RequestParam(value = "userPhone") String userPhone) {

        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);
        memberDTO.setMbrAddr(userAddr);
        memberDTO.setMbrPhone(userPhone);

        int result = 0;

        try {

            result = memberService.updateMemberInfo(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "회원정보가 변경되었습니다. 다시 로그인해주세요");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "회원정보 변경이 실패하였습니다. 관리자에게 문의하세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "실패하였습니다. 관리자에게 문의하세요");
        }

        return resultMap;
    }

    @RequestMapping(value = "/member/info/pwdconfirm")  /*비밀번호확인 페이지*/
    public ModelAndView pwdconfirm() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/pwdconfirm");
        return view;
    }

    @RequestMapping(value = "/member/info/delete")  /*회원탈퇴 페이지*/
    public ModelAndView deleteinfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/delete");
        return view;
    }

    @RequestMapping("/info/delete")  /*회원탈퇴*/
    @ResponseBody
    public Map<String, Object> deleteInfo(@RequestParam(value = "userId") String userId) {

        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);

        int result = 0;

        try {

            result = memberService.deleteMember(userId);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "회원탈퇴가 완료되었습니다.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "실패하였습니다. 관리자에게 문의하세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "실패하였습니다. 관리자에게 문의하세요");
        }

        return resultMap;
    }

    @RequestMapping(value = "/member/info/certify")  /*이메일인증 페이지*/
    public ModelAndView certifyemail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/certify");
        return view;
    }

    @RequestMapping("/info/certify") // 이메일 인증
    @ResponseBody
    public Map<String, Object> certifyEmail(@RequestParam(value = "id") String id) {
        Map<String, Object> resultMap = new HashMap<>();
        String emailKey;
        try {
            emailKey = memberService.certifyEmail(id);
            resultMap.put("resultCode", 200);
            resultMap.put("resultMsg", emailKey);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "전송실패 다시 시도하세요.");
        }
        return resultMap;
    }


    @RequestMapping("/certify/role/update")  /*이메일인증 role 수정*/
    @ResponseBody
    public Map<String, Object> updateRole(@RequestParam(value = "userId") String userId) {

        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);

        int result = 0;

        try {

            result = memberService.updateMemberRole(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "이메일 인증이 완료되었습니다.");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "실패하였습니다. 다시 시도해주세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "실패하였습니다. 관리자에게 문의하세요.");
        }

        return resultMap;
    }

    @RequestMapping("/member/update/point") //점수 업데이트
    @ResponseBody
    public Map<String,Object> updatePoint(@RequestParam(value = "userId") String userId){
        MemberDTO memberDTO = new MemberDTO();
        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);

        int result = 0;

        try {

            result = memberService.updateMemberPoints(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg","점수 업데이트");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg","점수 업데이트 400");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg","점수 업데이트 500");
        }

        return resultMap;
    }

    @RequestMapping("/member/update/grade") //등급 업데이트
    @ResponseBody
    public Map<String,Object> updateGrade(@RequestParam(value = "userId") String userId,
                                            @RequestParam(value = "userPoint") int userPoint){
        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();
        memberDTO.setMbrId(userId);
        memberDTO.setMbrPoints(userPoint);

        int result = 0;

        try {

            result = memberService.updateMemberGrade(memberDTO);

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg","등급 업데이트");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg","등급 업데이트 400");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg","등급 업데이트 500");
        }

        return resultMap;
    }

    @GetMapping(value = "/grade") /*등급기준 페이지*/
    public ModelAndView grade() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/information/grade");
        return view;
    }




}

