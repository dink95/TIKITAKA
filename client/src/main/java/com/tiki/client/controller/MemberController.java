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

    /*****************************로그인*********************************/
    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/login");
        return view;
    }


    @RequestMapping("/login/process")
    @ResponseBody
    public  Map<String, Object> loginAccess(@RequestParam(value = "userId") String userId,
                                            @RequestParam(value = "userPw") String userPw,
                                            HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        MemberDTO memberDTO = new MemberDTO();

        Map<String, Object> resultMap = new HashMap<>();

        memberDTO.setMbrId(userId);
        memberDTO.setMbrPwd(userPw);

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
        }catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", e.getMessage());
        }
        return resultMap;
    }

    @GetMapping(value="/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        if (request.getSession().getAttribute("mbrId") != null) {
            request.getSession().removeAttribute("mbrId");
        }
        view.setViewName("redirect:/");
        return view;
    }

    /********************************************************************************/

    /*****************************회원가입, 아이디 중복*********************************/

    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/signup");
        return view;
    }

    @RequestMapping("signup/idcheck")
    @ResponseBody
    public Map<String, Object> loginAccess(@RequestParam(value = "userId") String userId,
                                           HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        Map<String, Object> param = new HashMap<>();

        Map<String, Object> resultMap = new HashMap<>();

        param.put("userId", userId);

        MemberDTO member = null;
        //MemberDTO member  =  memberService.idCheck(param);

        try {
            if (member == null) {
                request.getSession().setAttribute("userInfo", member);
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

    @PostMapping("/login/join")
    @ResponseBody
    public  Map<String, Object> joinMember(@RequestBody MemberDTO memberDTO){

        Map<String, Object> resultMap = new HashMap<>();

        int result = 0;

        try{
            result = memberService.createMember(memberDTO);

            if(result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "회원가입을 완료하였습니다.");
            }else {
                resultMap.put("resultCode", 500);
                resultMap.put("resultMsg", "회원가입이 실패하였습니다.");
            }

        }catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "회원가입이 실패하였습니다.");
        }


        return resultMap;
    }

    /*********************************************************************************/

    @GetMapping(value = "/findid")
    public ModelAndView findid() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/findid");
        return view;
    }

    @GetMapping(value = "/findpwd")
    public ModelAndView findpwd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/findpwd");
        return view;
    }


}
