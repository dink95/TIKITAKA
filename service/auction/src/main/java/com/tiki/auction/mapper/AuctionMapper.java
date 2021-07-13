package com.tiki.auction.mapper;

import com.tiki.auction.domain.AuctionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionMapper {


    public int insertAuction(AuctionDTO auctionDTO);

    public int updateBid(AuctionDTO auctionDTO);


}
