package com.tiki.client.controller;

import com.tiki.client.domain.BuyListDTO;
import com.tiki.client.service.BuyListService;
import com.tiki.client.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BuyListController {

    @Autowired
    private BuyListService buyListService;

    @RequestMapping("/buyList/insert")  /*구매완료 insert*/
    @ResponseBody
    public Map<String,Object> insertBuyList(@RequestParam(value="prodNo") int prodNo,
                                            @RequestParam(value="buyerId") String buyerId) {
       BuyListDTO buyListDTO = new BuyListDTO();
       buyListDTO.setBuyerId(buyerId);
       buyListDTO.setProdNo(prodNo);
        Map<String, Object> resultMap = new HashMap<>();
        int result = 0;
        result = buyListService.insertBuyList(buyListDTO);
        if (result > 0) {
            resultMap.put("resultCode", 200);
        }else{
            resultMap.put("resultCode", 500);
        }
        return resultMap;
    }

    @RequestMapping("/buyList/all")  /*상품 아이디 검색 리스트(구매완료) */
    @ResponseBody
    public Map<String,Object> buyList(HttpServletRequest request ) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Map<String,Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list= buyListService.selectBuyList(idCookie.getValue());
            resultMap.put("dataQueryList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
