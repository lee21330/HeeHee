package com.shinhan.heehee.dto.response;

import java.sql.Date;
import java.util.List;

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
public class QnADTO {
	private int seqQnaBno;
	private String qnaTitle;
	private String qnaContent;
	private String qnaAns;
	private Date qnaTime;
	private String qnaOption;
	private int seqQnaOption;
	private String qnaOptionContent;
	private List<QnAImgDTO> imgList;
	
}
