package com.tiki.client.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CategoryDTO {

    /** 카테고리번호(pk) */
    public int catNo;

    /** 카테고리이름 */
    public String catNm;

}
