package com.shinhan.heehee.dto.response.pay;

import com.shinhan.heehee.dto.request.pay.PayRequestDTO;

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
public class PayResponseDTO {
	private int paySeq;
	private int amount;
	private String buyerId;
	private Integer aucSeq;
	private Integer sellSeq;
	private String buyerEmail;
	private String buyerTel;
	private String buyerAddr;
	
	public PayResponseDTO(PayRequestDTO reqDto) {
		this.paySeq = reqDto.getPaySeq();
		this.amount = reqDto.getAmount();
		this.buyerId = reqDto.getBuyerId();
		this.aucSeq = reqDto.getAucSeq();
		this.sellSeq = reqDto.getSellSeq();
	}
}

