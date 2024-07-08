package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertDeliveryDTO {
	private Integer dSeq;
	private Integer dCompanySeq;
	private Integer dNumber;
	private String dStatus;
	private Integer sSeq;
	private String buyerId;
}
