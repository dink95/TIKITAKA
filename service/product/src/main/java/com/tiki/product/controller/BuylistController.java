package com.tiki.product.controller;

import com.tiki.product.domain.BuylistDTO;
import com.tiki.product.domain.ChatDTO;
import com.tiki.product.service.BuylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuylistController {

    @Autowired
    private BuylistService buylistService;

    // 구매목록 insert
    @PostMapping("/buylist")
    public int buylistChat(@RequestBody BuylistDTO buylistDTO){

        BuylistDTO dto = buylistDTO;

        int prodNo = dto.getProdNo();

        return buylistService.insertBuylist(dto);
    }

    // 구매목록 보기 id
    @GetMapping(value = "/buylist/showbuylist/{buyerId}")
    public List selectAllBuylist(@PathVariable("buyerId") String buyerId) {

        List<BuylistDTO> buyList;
        BuylistDTO buylistDTO = new BuylistDTO();
        buylistDTO.setBuyerId(buyerId);
        buyList = buylistService.getBuylistList(buyerId);

        return buyList;

    }



}
