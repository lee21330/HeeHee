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
public class SaleDetailDTO {
	private int productSeq;
	private String proStatus;
	private Date psDate;
	private String category;
	private String detailCategory;
	private String prodName;
	private String articleTitle;
	private String introduce;
	private int dCompanySeq;
	private String dCompany;
	private String dNumber;
	private String dStatus;
	private int sSeq;
	private Date pCheck;
	private Date sCheck;
	private int productPrice;
	private int dCharge;
	private String deal;
	private String imgName;
}
