package com.tiki.member.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
