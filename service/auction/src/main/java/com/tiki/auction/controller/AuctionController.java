package com.tiki.auction.controller;


import com.tiki.auction.domain.AuctionDTO;
import com.tiki.auction.domain.BidDTO;
import com.tiki.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping("/auction/bid")
    public int insertBiddingProduct(@RequestBody BidDTO bidDTO){
        if(service.selectBidding(bidDTO)!=null){
            return service.updateBiddingProduct(bidDTO);
        }else {
            return service.insertBiddingProduct(bidDTO);
        }
    }


    @PatchMapping("/auction/bid")
    public int updateBiddingProduct(@RequestBody BidDTO bidDTO){
        return service.updateBiddingProduct(bidDTO);
    }


    @GetMapping("/auction/bid/{id}")
    public List selectAllBiddingProduct(@PathVariable("id") String id){
        List<BidDTO> bidList= service.selectAllBiddingProduct(id);
        return bidList;
    }

}
