package com.shinhan.heehee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealDTO {
	int S_SEQ;
	String BUYER_ID;
	char P_CHECK;
	char S_CHECK;
	int D_SEQ;
	int AUC_SEQ;
	int SELL_SEQ;
}
