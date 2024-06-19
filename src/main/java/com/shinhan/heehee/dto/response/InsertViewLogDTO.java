package com.shinhan.heehee.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertViewLogDTO {
	private int productSeq;
	private String id;
	private Date createDate;
}
