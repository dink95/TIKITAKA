package com.tiki.client.controller;

import com.tiki.client.domain.AuctionDTO;
import com.tiki.client.domain.BidDTO;
import com.tiki.client.domain.InsertProductDTO;
import com.tiki.client.domain.ProductDTO;
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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/auction/update.do")  /*경매업데이트*/
    public ModelAndView updateAuction(AuctionDTO auctionDTO) {
        ModelAndView view = new ModelAndView();

        int result = 0;
        try {
            result = auctionService.updateBid(auctionDTO);

            if (result > 0) {
                view.addObject("resultCode", 200);
            }else {
                view.addObject("resultCode", 400);
            }
        } catch (Exception e) {
            view.addObject("resultCode", 500);
        }
        view.setViewName("product/create_result");
        return view;
    }

    @RequestMapping(value = "/auction/select.do") /*경매 상품 상세 정보*/
    @ResponseBody
    public Map<String,Object> productDetail(@RequestParam(value = "prodNo") Integer prodNo,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        Map<String ,Object> resultMap = new HashMap<>();
        Cookie idCookie = WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        try {
            AuctionDTO auctionDTO = auctionService.selectAuctionByProdNo(prodNo);
            resultMap.put("auctionDetail", auctionDTO);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("detail error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/bid/select") /*입찰중 상품들 번호 조회*/
    @ResponseBody
    public Map<String,Object> selectAllBiddingProduct(HttpServletRequest request,
                                            HttpServletResponse response) {
        List<Object> list = null;
        Map<String ,Object> resultMap = new HashMap<>();
        Cookie idCookie = WebUtils.getCookie(request, "mbrId");
        try {
            list= auctionService.selectAllBiddingProduct(idCookie.getValue());
            resultMap.put("dataBidList", list);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/bid/create")  /*입찰중 insert*/
    @ResponseBody
    public Map<String,Object> insertBiddingProduct(BidDTO bidDTO) {
        Map<String,Object> resultMap = new HashMap<>();
        int result = 0;

        try {
            result = auctionService.insertBiddingProduct(bidDTO);
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

    @RequestMapping("/bid/update")  /*입찰 update*/
    public ModelAndView updateBid(BidDTO bidDTO) {
        ModelAndView view = new ModelAndView();

        int result = 0;
        try {
            result = auctionService.updateBiddingProduct(bidDTO);
            System.out.println(bidDTO);
            if (result > 0) {
                view.addObject("resultCode", 200);
            }else {
                view.addObject("resultCode", 400);
            }
        } catch (Exception e) {
            System.out.println("!@#!@#!2");
            view.addObject("resultCode", 500);
        }
        view.setViewName("product/create_result");
        return view;
    }

}
