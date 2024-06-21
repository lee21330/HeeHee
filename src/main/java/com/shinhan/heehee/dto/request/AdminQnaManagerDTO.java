package com.shinhan.heehee.dto.request;

import java.sql.Date;

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
public class AdminQnaManagerDTO {
	
	//번호 - QNA_BOARD
	private int seq_faq_bno;
	
	//유형 - QNA_OPTION
	private String qna_option;
	
	//제목 - QNA_BOARD
	private String qna_title;
	
	//작성자ID - QNA_BOARD
	private String id;
	
	//작성일 - QNA_BOARD
	private Date qna_time;
	
}
