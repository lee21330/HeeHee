package com.shinhan.heehee.dto.response;


import java.sql.Date;

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
public class MainProdSearchDTO {
	private String imgName;
	private String articleTitle;
	private int productPrice;
	private Date createDate;
	private int tablePk;
	
}
