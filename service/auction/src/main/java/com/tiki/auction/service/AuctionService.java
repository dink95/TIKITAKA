package com.tiki.auction.service;


import com.tiki.auction.domain.AuctionDTO;
import com.tiki.auction.domain.BidDTO;

import java.util.List;

public interface AuctionService {


    public int insertAuction(AuctionDTO auctionDTO);

    public int updateBid(AuctionDTO auctionDTO);

    public AuctionDTO selectAuction(int prodNo);

    public int insertBiddingProduct(BidDTO bidDTO);

    public int updateBiddingProduct(BidDTO bidDTO);

    public List<BidDTO> selectAllBiddingProduct(String mbrId);


}
