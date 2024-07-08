package com.shinhan.heehee.dto.response.auction;

import java.sql.Date;

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
public class SchedulerBidDTO {
	private int productSeq;
	private Date expTime;
	private String aucStatus;
	private String sellerId;
	private String buyerId;
	private int bidPrice;
	
}
