package com.shinhan.heehee.dto.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDTO {
	int alNum;
	String id;
	int cateNum;
	int reqSeq;
	String alContent;
	String alCheck;
	Date alDate;
}