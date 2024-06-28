package com.shinhan.heehee.dto.response;


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
public class MainProdRankDTO {
	private int imgSeq;
	private String imgName;
	private String articleTitle;
	private String introduce;
	private int productPrice;
	private int tablePk;
	private int imgCateSeq;
	private int productSeq;
}
