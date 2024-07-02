package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsertQnADTO {
	private int seqQnaBno;
	private String id;
	private String qnaTitle;
	private String qnaContent;
	private String seqQnaOption;
}
