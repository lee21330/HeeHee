package com.shinhan.heehee.dto.response.auction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuctionProdInfoDTO {
	private int productSeq;
	private int aucPrice;
	private int increasePrice;
	private String auctionTitle;
	private String introduce;
	private String condition;
	private String expDate;
	private String expTime;
	private String aucStatus;
	private int auctionSeq;
	private String category;
	private String detailCategory;
	private String sellerId;
	private int joinCount;
	private String currentId;
	private String currentNick;
}