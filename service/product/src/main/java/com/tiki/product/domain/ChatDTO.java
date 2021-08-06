package com.tiki.product.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {

    /** 인덱스(pk) */
    private int chatIdx;

    /** 방 번호 */
    private int roomNo;

    /** 제품번호(fk) */
    private int prodNo;

    /** 보내는 사람(fk) */
    private String sendId;

    /** 받는 사람(fk) */
    private String recipientId;

    /** 채팅내용 */
    private String content;

    /** 보낸 날짜 */
    private LocalDateTime sendtime;

    public ChatDTO(int roomNo,int prodNo, String sendId, String recipientId, String content){
        this.roomNo=roomNo;
        this.prodNo= prodNo;
        this.sendId=sendId;
        this.recipientId=recipientId;
        this.content=content;
    };

    public ChatDTO(){};

}