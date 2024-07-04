package com.shinhan.heehee.dto.request.auction;

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
public class AuctionHistoryDTO {
	private int aucProdSeq;
	private String userId;
	private int bidPrice;
}
