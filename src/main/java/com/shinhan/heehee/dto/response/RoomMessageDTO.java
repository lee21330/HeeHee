package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomMessageDTO {
	private int msgId;
	private String content;
	private String sendTime;
	private String readCheck;
	private String sender;
	private String receiver;
}
