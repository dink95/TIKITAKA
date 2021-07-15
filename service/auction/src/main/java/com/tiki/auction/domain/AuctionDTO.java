package com.tiki.auction.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class AuctionDTO {

    private LocalDateTime aucStartTime;
    private LocalDateTime aucEndTime;
    private int startingBid;
    private  int endBid;
    private int prodNo;

    private String mbrId;

}
