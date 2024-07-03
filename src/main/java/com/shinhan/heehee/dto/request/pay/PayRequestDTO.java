package com.shinhan.heehee.dto.request.pay;

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
public class PayRequestDTO {
	private int paySeq;
	private String payName;
	private int amount;
	private String buyerId;
	private Integer aucSeq;
	private Integer sellSeq;
}
