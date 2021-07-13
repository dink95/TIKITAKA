package com.tiki.auction.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class Chat{
	private String sender;
	private String content;
	private Date sendDate;

}