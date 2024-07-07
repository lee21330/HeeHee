package com.shinhan.heehee.dto.request.auction;

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
public class AuctionInsertDTO {
	private List<MultipartFile> uploadFiles;
	private int productSeq;
	private int startPrice;
	private int increasePrice;
	private String auctionTitle;
	private String condition;
	private String introduce;
	private String expTime;
	private int auctionSeq;
	private String sellerId;	
}