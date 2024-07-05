package com.shinhan.heehee.dto.response.auction;

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
public class AuctionImgsDTO {
	private int imgSeq;
	private String imgName;
	private int tablePk;
	private int imgCateSeq;
	private String id;
}
