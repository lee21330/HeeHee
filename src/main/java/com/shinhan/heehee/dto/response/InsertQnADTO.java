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
	private String ID;
	private String QNA_TITLE;
	private String QNA_CONTENT;
	private String QNA_FILE;
	private String SEQ_QNA_OPTION;
}
