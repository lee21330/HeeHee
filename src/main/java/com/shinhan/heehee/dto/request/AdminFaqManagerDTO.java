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
public class AdminFaqManagerDTO {
	
	//번호 - FAQ_BOARD
	private int seq_faq_bno;
	
	//유형 - QNA_OPTION
	private String qna_option;
	
	//제목 - FAQ_BOARD
	private String faq_content;
	
	//내용 - FAQ_BOARD
	private String faq_ans;
	
	//작성자ID - FAQ_BOARD
	private String id;
	
	//작성일 - FAQ_BOARD
	private Date faq_time;
	
	//수정용 임시 변수 - QNA_OPTION
	private int seq_qna_option;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminFaqManagerDTO(int seq_faq_bno) {
		this.seq_faq_bno = seq_faq_bno;
	}
	
	//수정용 - 파라미터 4개 생성자 추가
	public AdminFaqManagerDTO(int seq_faq_bno, int seq_qna_option, String faq_content, String faq_ans) {
		this.seq_faq_bno = seq_faq_bno;
		this.seq_qna_option = seq_qna_option;
		this.faq_content = faq_content;
		this.faq_ans = faq_ans;
	}
}
