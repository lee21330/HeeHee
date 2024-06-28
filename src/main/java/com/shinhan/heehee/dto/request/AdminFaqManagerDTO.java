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
	private int seqFaqBno;
	
	//유형 - QNA_OPTION
	private String qnaOption;
	
	//제목 - FAQ_BOARD
	private String faqContent;
	
	//내용 - FAQ_BOARD
	private String faqAns;
	
	//작성자ID - FAQ_BOARD
	private String id;
	
	//작성일 - FAQ_BOARD
	private Date faqTime;
	
	//수정용 임시 변수 - QNA_OPTION
	private int seqQnaOption;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminFaqManagerDTO(int seqFaqBno) {
		this.seqFaqBno = seqFaqBno;
	}
	
	//수정용 - 파라미터 4개 생성자 추가
	public AdminFaqManagerDTO(int seqFaqBno, int seqQnaOption, String faqContent, String faqAns) {
		this.seqFaqBno = seqFaqBno;
		this.seqQnaOption = seqQnaOption;
		this.faqContent = faqContent;
		this.faqAns = faqAns;
	}
}