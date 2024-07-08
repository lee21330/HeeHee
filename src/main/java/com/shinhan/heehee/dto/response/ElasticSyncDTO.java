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
public class ElasticSyncDTO {
	private String gubun;
	private int productSeq;
	private int price;
	private String title;
	private String introduce;
	private String idate;
	private String imgName;
	private int cateNum;
}
