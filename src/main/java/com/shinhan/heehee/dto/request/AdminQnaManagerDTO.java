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
	private int seq_qna_bno;
	
	//유형 - QNA_OPTION
	private String qna_option;
	
	//제목 - QNA_BOARD
	private String qna_title;
	
	//답변내용 - QNA_BOARD
	private String qna_content;
	
	//작성자ID - QNA_BOARD
	private String id;
	
	//작성일 - QNA_BOARD
	private Date qna_time;
	
	//자바스크립트 변수 : 1:1 답변내용
	private String newValue;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminQnaManagerDTO(int seq_qna_bno) {
		this.seq_qna_bno = seq_qna_bno;
	}
	
	//수정, 답변용 - 파라미터 2개 생성자 추가
	public AdminQnaManagerDTO(int seq_qna_bno, String newValue) {
		this.seq_qna_bno = seq_qna_bno;
		this.newValue = newValue;
	}
	
}
