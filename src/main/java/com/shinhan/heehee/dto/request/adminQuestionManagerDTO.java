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
	private int seqQnaOption;
	
	//유형 - QNA_OPTION
	private String qnaOption;
	
	//내용 - QNA_OPTION
	private String qnaOptionContent;
	
	//작성자ID - QNA_OPTION
	private String id;
	
	//작성일 - QNA_OPTION
	private Date createDate;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public adminQuestionManagerDTO(int seqQnaOption) {
		this.seqQnaOption = seqQnaOption;
	}
	
	//신규등록용 - 파라미터 2개 생성자 추가
	public adminQuestionManagerDTO(String qnaOption, String qnaOptionContent) {
		this.qnaOption = qnaOption;
		this.qnaOptionContent = qnaOptionContent;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public adminQuestionManagerDTO(int seqQnaOption, String qnaOption, String qnaOptionContent) {
		this.seqQnaOption = seqQnaOption;
		this.qnaOption = qnaOption;
		this.qnaOptionContent = qnaOptionContent;
	}
}
