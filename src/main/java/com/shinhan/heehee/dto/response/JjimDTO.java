package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JjimDTO {
	private int productSeq;
	private String articleTitle;
	private int productPrice;
	private String deal;
	private String id;
	private String imgName;
	private String userId;
}