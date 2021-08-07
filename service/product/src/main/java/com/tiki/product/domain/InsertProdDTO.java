package com.tiki.product.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class InsertProdDTO {

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

    /** 상세내용 **/
    private String content;

    /** 사진 개수 **/
    private int imgCount;

    /** 채팅방 개수 **/
    private Integer roomCount;
}
