package com.tiki.client.controller;

import com.tiki.client.domain.AuctionDTO;
import com.tiki.client.domain.InsertProductDTO;
import com.tiki.client.service.AuctionService;
import com.tiki.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping(value = "/auction/create") /*경매등록 페이지*/
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        view.setViewName("auction/create");
        return view;
    }

    @GetMapping(value = "/auction/list") /*경매상품 페이지*/
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        view.setViewName("auction/list");
        return view;
    }

    @GetMapping(value = "/auction/detail") /*경매상품 상세 페이지*/
    public ModelAndView detail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("auction/detail");
        return view;
    }

    @RequestMapping("/auction/create.do")  /*경매상품등록*/
    @ResponseBody
    public Map<String,Object> createAuction(AuctionDTO auctionDTO ) {
        Map<String,Object> resultMap = new HashMap<>();
        int result = 0;
        try {
            result = auctionService.insertAuctionProduct(auctionDTO);
            System.out.println(result);
            if (result > 0) {
                resultMap.put("resultCode", 200);
            }else {
                resultMap.put("resultCode", 400);
            }
        } catch (Exception e) {
            resultMap.put("resultCode", 500);
        }

        return resultMap;
    }

}
