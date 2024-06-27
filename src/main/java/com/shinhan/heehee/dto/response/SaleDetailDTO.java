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
	private String proStatus;
	private Date psDate;
	private String articleTitle;
	private int productPrice;
	private int dCharge;
	private String deal;
	private String imgName;
}
