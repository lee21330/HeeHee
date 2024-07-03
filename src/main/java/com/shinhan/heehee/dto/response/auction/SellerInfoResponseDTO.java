package com.shinhan.heehee.dto.response.auction;

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
public class SellerInfoResponseDTO {
	private String id;
	private String nickName;
	private String profileImg;
	private String userIntroduce;
	private int userRating;
}
