package com.shinhan.heehee.dto.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailAucDTO {
//	private int productSeq;
//	private String aucStatus;
//	private Date psDate;
//	private String category;
//	private String detailCategory;
//	private String actionTitle;
//	private String introduce;
//	
//	private int dCompanySeq;
//	private String dCompany;
//	private String dNumber;
//	private String dStatus;
//	private int sSeq;
//	private Date pCheck;
//	private Date sCheck;
//	
//	private int dCharge;
//	private String deal;
//	private String imgName;
//	private String buyerId;
	private int productSeq;
	private int aucPrice;
	private String auctionTitle;
	private String expDate;
	private String expTime;
	private String imgName;
	private String aucStatus;
	private String accBanReason;
}
