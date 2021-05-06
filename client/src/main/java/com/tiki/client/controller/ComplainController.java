package com.tiki.client.controller;

import com.tiki.client.domain.ComplainDTO;
import com.tiki.client.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
    public class ComplainController {

        @Autowired
        private ComplainService complainService;

        @GetMapping(value = "/complain/index") /*신고하기 페이지*/
        public ModelAndView complain() {
            ModelAndView view = new ModelAndView();
            view.setViewName("complain/index");
            return view;
        }

    @RequestMapping("/complain/index.do")  /*신고하기*/
    public ModelAndView createComplain(ComplainDTO complainDTO,  @RequestParam(value = "prodNo") Integer prodNo) {
        ModelAndView view = new ModelAndView();
        int result = 0;
        System.out.println(complainDTO.getCompNm());
        try {
            result = complainService.insertComplain(complainDTO);

            if (result > 0) {
                view.addObject("resultCode", 202);
                view.addObject("prodNo", prodNo);
            }else {
                view.addObject("resultCode", 500);
            }
        } catch (Exception e) {
            view.addObject("resultCode", 500);
        }
        view.setViewName("product/create_result");
        return view;
    }

}

