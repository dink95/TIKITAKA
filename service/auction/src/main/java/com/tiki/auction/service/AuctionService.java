package com.tiki.auction.service;


import com.tiki.auction.domain.AuctionDTO;

public interface AuctionService {


    public int insertAuction(AuctionDTO auctionDTO);

    public int updateBid(AuctionDTO auctionDTO);

    public AuctionDTO selectAuction(int prodNo);


}
