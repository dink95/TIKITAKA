package com.tiki.client.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BidDTO {

    private int bidProdNo;
    private String bidBidder;
    private int bidBid;


}
