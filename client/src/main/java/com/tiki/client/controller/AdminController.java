package com.tiki.client.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping(value = "/admin/complain/list") /*신고리스트 페이지*/
    public ModelAndView complainlist() {
        ModelAndView view = new ModelAndView();
        view.setViewName("admin/complain/list");
        return view;
    }

    @GetMapping(value = "/admin/complain/detail") /*신고상세내용 페이지*/
    public ModelAndView complaindetail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("admin/complain/detail");
        return view;
    }

}