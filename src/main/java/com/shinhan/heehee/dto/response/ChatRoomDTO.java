package com.shinhan.heehee.dto.response;

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
public class ChatRoomDTO {
	private int id;
	private String lastcontent;
	private String sendtime;
	private String receiverid;
	private String receivernickname;
	private String receiverimg;
	private int unreadcount;
	private int maxmessageno;
}
