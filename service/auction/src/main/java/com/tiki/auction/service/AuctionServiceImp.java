package com.tiki.auction.service;

import com.tiki.auction.domain.BidDTO;
import com.tiki.auction.mapper.AuctionMapper;
import com.tiki.auction.domain.AuctionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int insertBiddingProduct(BidDTO bidDTO) {
        return mapper.insertBiddingProduct(bidDTO);
    }

    @Override
    public int updateBiddingProduct(BidDTO bidDTO) {
        return mapper.updateBiddingProduct(bidDTO);
    }

    @Override
    public List<BidDTO> selectAllBiddingProduct(String mbrId) {
        return mapper.selectAllBiddingProduct(mbrId);
    }

    @Override
    public BidDTO selectBidding(BidDTO bidDTO) {
        return mapper.selectBidding(bidDTO);
    }
}
