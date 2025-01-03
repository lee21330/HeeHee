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
public class JjimDTO {
	private int productSeq;
	private String id;
	private String userId;
	private int JJIM_CNT;
}
