package com.tiki.complain.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplainDTO {

    int compIdx;
    String compNm;
    String compCon;
    String spt;
    String repo;
    String writingTime;
    boolean readCheck;

}
