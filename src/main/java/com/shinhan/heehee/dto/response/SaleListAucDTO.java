package com.shinhan.heehee.dto.response;

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
public class SaleListAucDTO {
	private int productSeq;
	private int aucPrice;
	private String auctionTitle;
	private String expDate;
	private String expTime;
	private String imgName;
	private String aucStatus;
	private String accBanReason;
}
