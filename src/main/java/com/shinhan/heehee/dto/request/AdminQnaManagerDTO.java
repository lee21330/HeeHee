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
	private int seqQnaBno;
	
	//유형 - QNA_OPTION
	private String qnaOption;
	
	//제목 - QNA_BOARD
	private String qnaTitle;
	
	//답변내용 - QNA_BOARD
	private String qnaContent;
	
	//작성자ID - QNA_BOARD
	private String id;
	
	//작성일 - QNA_BOARD
	private Date qnaTime;
	
	//답변내용 열람용
	private String qnaAns;
	
	//자바스크립트 변수 : 1:1 답변내용
	private String newValue;
	
	//페이징 처리를 위한 변수
	private int size;
	private int page;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminQnaManagerDTO(int seqQnaBno) {
		this.seqQnaBno = seqQnaBno;
	}
	
	//수정, 답변용 - 파라미터 2개 생성자 추가
	public AdminQnaManagerDTO(int seqQnaBno, String newValue) {
		this.seqQnaBno = seqQnaBno;
		this.newValue = newValue;
	}
	
}
