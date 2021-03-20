package com.tiki.client.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private  String mbrId;
    private String mbrPwd;
    private String mbrNm;
    private String mbrBday;
    private  String mbrAddr;
    private  boolean mbrRole;
    private  int mbrPoints;
    private  String mbrGrade;
    private  String mbrEmail;
    private  String mbrPhone;




}
