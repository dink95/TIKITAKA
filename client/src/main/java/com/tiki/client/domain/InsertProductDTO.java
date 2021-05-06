package com.tiki.client.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsertProductDTO {


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

    /** 상품코드 */
    private String prodCo;

    /** 상세정보 */
    private String content;

    /** 사진 개수 **/
    private int imgCount;

}