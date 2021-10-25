package com.tiki.client.controller;

import com.tiki.client.domain.ComplainDTO;
import com.tiki.client.domain.MemberDTO;
import com.tiki.client.domain.ProductDTO;
import com.tiki.client.domain.paging.PagingDTO;
import com.tiki.client.domain.paging.SearchDTO;
import com.tiki.client.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public ModelAndView createComplain(ComplainDTO complainDTO, @RequestParam(value = "prodNo") Integer prodNo,
                                       HttpServletRequest request) {
        Cookie idCookie = WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");

        ModelAndView view = new ModelAndView();
        int result = 0;
        System.out.println(complainDTO.getCompNm());
        try {
            result = complainService.insertComplain(complainDTO,idCookie.getValue(),tokenCookie.getValue());

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

    //신고리스트 페이징
    @RequestMapping(value="admin/complain/list")
    public ModelAndView compList(@ModelAttribute SearchDTO searchDTO,
                                 HttpServletRequest request){
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");

        ModelAndView view = new ModelAndView();
        view.setViewName("admin/complain/list");

        List<ComplainDTO> compList = null;

        try{
            int totalCount = complainService.getTotalCount(searchDTO,idCookie.getValue(),tokenCookie.getValue());

            //페이징 처리 객체를 선언한다.
            PagingDTO pagingDTO = new PagingDTO();
            //구해온 전체 리스트 개수를 페이지처리 객체에 넣는다.
            pagingDTO.setTotalCount(totalCount);
            //클라이언트에서 넘어온 이동할 페이지정보를 페이징처리 객체에 넣어준다
            pagingDTO.setCurrentPage(searchDTO.getCurrentPage());

            searchDTO.setStart(pagingDTO.getStartRow());
            searchDTO.setEnd(pagingDTO.getCountPerPage());

            //페이지에 뿌릴 데이터를 가져온다.
            compList = complainService.selectAllComplains(searchDTO,idCookie.getValue(),tokenCookie.getValue());
            view.addObject("currentPage", searchDTO.getCurrentPage());
            view.addObject("pageHtml",pagingDTO.getPager());
            view.addObject("compList",compList);

            if(compList != null){
                view.addObject("listSize", totalCount);
            }else{
                view.addObject("listSize",0);
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        return view;
    }


    @GetMapping(value = "/admin/complain/detail") /*신고디테일 페이지*/
    public ModelAndView detail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("admin/complain/detail");
        return view;
    }

    @RequestMapping("/comp/detail") /*신고상세내용 조회*/
    @ResponseBody
    public Map<String, Object> CompDetailByIndex(@RequestParam(value = "compIdx") int compIdx,
                                                 HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");

        Map<String, Object> resultMap = new HashMap<>();
        try {
            ComplainDTO complainDTO = complainService.selectComplainDetailByIndex(compIdx,idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("compDetail", complainDTO);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("detail error");
        }
        return resultMap;
    }

    @RequestMapping("/comp/delete")  /*신고내용삭제*/
    @ResponseBody
    public Map<String, Object> deleteComp(@RequestParam(value = "compIdx") int compIdx,
                                          HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        ComplainDTO complainDTO = new ComplainDTO();

        Map<String, Object> resultMap = new HashMap<>();
        complainDTO.setCompIdx(compIdx);

        int result = 0;

        try {

            result = complainService.deleteComplain(compIdx,idCookie.getValue(),tokenCookie.getValue());

            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "삭제완료");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "삭제실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/complain/repo") /*용의자로 신고내용 조회*/
    @ResponseBody
        public ModelAndView compList(@RequestParam(value="repo") String repo,
                                     HttpServletRequest request){
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");

        ModelAndView view = new ModelAndView();
            view.setViewName("admin/complain/list");
            SearchDTO searchDTO =new SearchDTO();
            List<ComplainDTO> compList = null;

            try{
                int totalCount = complainService.getTotalCount(searchDTO,idCookie.getValue(),tokenCookie.getValue());

                //페이징 처리 객체를 선언한다.
                PagingDTO pagingDTO = new PagingDTO();
                //구해온 전체 리스트 개수를 페이지처리 객체에 넣는다.
                pagingDTO.setTotalCount(totalCount);
                //클라이언트에서 넘어온 이동할 페이지정보를 페이징처리 객체에 넣어준다
                pagingDTO.setCurrentPage(searchDTO.getCurrentPage());

                searchDTO.setStart(pagingDTO.getStartRow());
                searchDTO.setEnd(pagingDTO.getCountPerPage());


                //페이지에 뿌릴 데이터를 가져온다.
                compList = complainService.selectByRepo(repo,idCookie.getValue(),tokenCookie.getValue());
                view.addObject("currentPage", searchDTO.getCurrentPage());
                view.addObject("pageHtml",pagingDTO.getPager());
                view.addObject("compList",compList);

                if(compList != null){
                    view.addObject("listSize", totalCount);
                }else{
                    view.addObject("listSize",0);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return view;
    }

}

