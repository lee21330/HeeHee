package com.shinhan.heehee.dto.requset;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminFaqBoardDTO {
	
	private int seq_faq_bno;
	private String faq_content;
	private String faq_ans;
	private String id;
	private int option_seq;
	private Date faq_time;

}
