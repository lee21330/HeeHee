package com.shinhan.heehee.dto.request.auction;

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
public class InsertDealHistoryDTO {
	private String buyerId;
	private int aucSeq;
}
