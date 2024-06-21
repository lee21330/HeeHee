package com.shinhan.heehee.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SellProDTO {
	private int productSeq;
	private String articleTitle;
	private int productPrice;
	private String introduce;
	private String condition;
	private String proStatus;
	private String deal;
	private int dCharge;
	private int selectSeq;
	private String id;
	private Date createDate;
}
