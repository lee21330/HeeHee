package com.shinhan.heehee.dto.response.auction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommandAucDTO {
	private int productSeq;
	private int startPrice;
	private int increasePrice;
	private String auctionTitle;
	private String condition;
	private String introduce;
}