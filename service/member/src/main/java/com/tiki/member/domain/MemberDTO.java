package com.tiki.member.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

    public MemberDTO( String name, String id, String phone){
        this.mbrNm = name;
        this.mbrId = id;
        this.mbrPhone = phone;
    }

}
