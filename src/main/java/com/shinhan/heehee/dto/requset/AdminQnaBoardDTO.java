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
public class AdminQnaBoardDTO {
	
	private int seq_qna_bno;
	private String id;
	private String qna_title;
	private String qna_content;
	private String qna_file;
	private String qna_ans;
	private Date qna_time;
	private int seq_qna_option;

}
