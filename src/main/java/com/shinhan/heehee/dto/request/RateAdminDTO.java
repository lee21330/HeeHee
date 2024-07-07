package com.shinhan.heehee.dto.request;

import java.util.Date;

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
public class RateAdminDTO {
	private int selectedRating;
	private int productSeq; //받아와야할거
	private String sellerId; // 물품 올린사람 받아와야할거
	private int userRating; // 받아와야할거
	private String userId; // 로그인한 사람 / controller에서 세팅해줌
}