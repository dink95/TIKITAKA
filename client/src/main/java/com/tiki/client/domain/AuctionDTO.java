package com.tiki.client.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuctionDTO {

        private LocalDateTime aucStartTime;

        private LocalDateTime aucEndTime;

        private int startingBid;

        private  int endBid;

        private int prodNo;

        private String mbrId;

}
