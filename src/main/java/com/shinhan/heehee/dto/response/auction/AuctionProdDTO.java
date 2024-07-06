package com.shinhan.heehee.dto.response.auction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuctionProdDTO {
	private int productSeq;
	private int aucPrice;
	private String auctionTitle;
	private String expDate;
	private String expTime;
	private String imgName;
}
