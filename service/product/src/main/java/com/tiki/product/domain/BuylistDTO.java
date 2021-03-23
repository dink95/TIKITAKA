package com.tiki.product.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuylistDTO {

    /** 제품번호(fk) */
    public int prodNo;

    /** 구매자ID(fk) */
    public String buyerId;

}
