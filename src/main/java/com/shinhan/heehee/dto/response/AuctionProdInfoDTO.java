package com.shinhan.heehee.dto.response;

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
	private String expDate;
	private String expTime;
	private String aucStatus;
	private int auctionSeq;
	private String sellerId;
	private String imgName;
}