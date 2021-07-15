package com.tiki.auction.service;

import com.tiki.auction.mapper.AuctionMapper;
import com.tiki.auction.domain.AuctionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImp implements AuctionService {

    @Autowired
    private AuctionMapper mapper;


    @Override
    public int insertAuction(AuctionDTO auctionDTO) {
        return mapper.insertAuction(auctionDTO);
    }

    @Override
    public int updateBid(AuctionDTO auctionDTO) {
        return mapper.updateBid(auctionDTO);
    }

    @Override
    public AuctionDTO selectAuction(int prodNo) {
        return mapper.selectAuction(prodNo);
    }
}
