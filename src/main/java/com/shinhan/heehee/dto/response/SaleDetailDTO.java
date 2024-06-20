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
	private String pro_status;
	private Date ps_date;
	private String articleTitle;
	private int productPrice;
	private int d_charge;
	private String deal;
	private String id;
	private String imgName;
}
