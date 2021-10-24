package com.tiki.auction.mapper;

import com.tiki.auction.domain.AuctionDTO;
import com.tiki.auction.domain.BidDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {

    public int insertAuction(AuctionDTO auctionDTO);

    public int updateBid(AuctionDTO auctionDTO);

    public AuctionDTO selectAuction(int prodNo);

    public int insertBiddingProduct(BidDTO bidDTO);

    public int updateBiddingProduct(BidDTO bidDTO);

    public List<BidDTO> selectAllBiddingProduct(String mbrId);


}
