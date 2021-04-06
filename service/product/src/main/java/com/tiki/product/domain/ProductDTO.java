package com.tiki.product.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

    /** 제품번호(pk) */
    private int prodNo;

    /** 제품이름 */
    private String prodNm;

    /** 제품가격 */
    private int prodPrc;

    /** 카테고리번호(fk) */
    private int catNo;

    /** 판매자ID(fk) */
    private String selId;

    /** 거래방법 */
    private String way;

    /** 등록날짜 */
    private LocalDateTime wday;

    /** 상품코드 */
    private String prodCo;

    /** 조회수 */
    private int prodView;

}
