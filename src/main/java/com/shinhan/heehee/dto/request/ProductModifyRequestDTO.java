package com.shinhan.heehee.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
public class ProductModifyRequestDTO {
	private int prodSeq;
	private List<MultipartFile> uploadFiles;
	private String[] delArr;
	private String articleTitle;
	private int productPrice;
	private String introduce;
	private String state;
	private String deal;
	private int dCharge;
	private String userId;
}
