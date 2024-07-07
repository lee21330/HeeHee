package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseListDTO {
	private int productSeq;
	private char P_CHECK;
	private char S_CHECK;
	private String articleTitle;
	private String introduce;
	private int productPrice;
	private String imgName;
}
