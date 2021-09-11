package com.tiki.auction.controller;


import com.tiki.auction.domain.AuctionDTO;
import com.tiki.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuctionController {

    @Autowired
    private AuctionService service;

    @PostMapping("/auction")
    public int insertAuction(@RequestBody AuctionDTO auctionDTO){
        return service.insertAuction(auctionDTO);
    }

    @PatchMapping("/auction")
    public int updateBid(@RequestBody AuctionDTO auctionDTO){
        return service.updateBid(auctionDTO);
    }



    @GetMapping("/auction/{prodNo}")
    public AuctionDTO selectAuction(@PathVariable("prodNo") int prodNo){
        return service.selectAuction(prodNo);
    }
}
