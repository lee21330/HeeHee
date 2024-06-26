package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdDetailImgDTO {
	private int imgSeq;
	private String imgName;
	private int tablePk;
	private int productSeq;
}
