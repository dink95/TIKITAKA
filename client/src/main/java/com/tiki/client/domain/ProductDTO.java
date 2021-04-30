package com.tiki.client.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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

    /** 네고여부 */
    private int nego;

    /** 등록날짜 */
    private LocalDateTime wday;

    /** 상품코드 */
    private String prodCo;

    /** 조회수 */
    private int prodView;

    public ProductDTO(String prodNm, int prodPrc, int catNo, String selId, String way, int nego, String prodCo){
        this.prodNm=prodNm;
        this.prodPrc= prodPrc;
        this.catNo=catNo;
        this.selId=selId;
        this.way=way;
        this.nego= nego;
        this.prodCo=prodCo;
    };

    public ProductDTO(){};

}
