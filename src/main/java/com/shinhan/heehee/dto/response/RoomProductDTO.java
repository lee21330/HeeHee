package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomProductDTO {
	private int productSeq;
	private String productType;
	private String productImg;
	private int productPrice;
	private String productName;
	private String sellProStatus;
	private String payStatus;
	private String status;
}
