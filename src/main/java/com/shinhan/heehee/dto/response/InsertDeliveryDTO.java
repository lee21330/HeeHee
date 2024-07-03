package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertDeliveryDTO {
	private Integer dSeq;
	private Integer dCompanySeq;
	private String dNumber;
	private String dStatus;
	private String sSeq;
	private String buyerId;
}
