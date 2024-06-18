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
	private int productCateSeq;
	private String category;
	private String detailCategory;
	private String prodName;
	private Date createDate;
	private String imgName;
	private String profileImg;
	private String nickName;
	private String userRating;
	private String userIntroduce;
	private int viewCnt;
	private int jjimCnt;
	private String agoTime;
}









