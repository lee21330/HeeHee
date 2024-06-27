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
public class adminQuestionManagerDTO {
	
	//번호 - QNA_OPTION
	private int seq_qna_option;
	
	//유형 - QNA_OPTION
	private String qna_option;
	
	//내용 - QNA_OPTION
	private String qna_option_content;
	
	//작성자ID - QNA_OPTION
	private String id;
	
	//작성일 - QNA_OPTION
	private Date create_date;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public adminQuestionManagerDTO(int seq_qna_option) {
		this.seq_qna_option = seq_qna_option;
	}
	
	//신규등록용 - 파라미터 2개 생성자 추가
	public adminQuestionManagerDTO(String qna_option, String qna_option_content) {
		this.qna_option = qna_option;
		this.qna_option_content = qna_option_content;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public adminQuestionManagerDTO(int seq_qna_option, String qna_option, String qna_option_content) {
		this.seq_qna_option = seq_qna_option;
		this.qna_option = qna_option;
		this.qna_option_content = qna_option_content;
	}
}
