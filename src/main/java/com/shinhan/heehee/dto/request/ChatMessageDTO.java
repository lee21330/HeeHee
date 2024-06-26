package com.shinhan.heehee.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
	private int msgId;
	private int imgId;
	private int roomId;
	private String sender;
	private String receiver;
	private String content;
	private String sendTime;
	private List<MultipartFile> imgs;
}