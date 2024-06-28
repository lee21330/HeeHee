package com.shinhan.heehee.dto.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmDTO {
	int alNum;
	String id;
	int cateNum;
	int reqSeq;
	String alContent;
	String alCheck;
	Date alDate;
}