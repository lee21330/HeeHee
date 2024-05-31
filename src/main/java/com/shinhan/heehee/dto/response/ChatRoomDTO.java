package com.shinhan.heehee.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {
	private int id;
	private String lastContent;
	private String sendTime;
	private String receiverId;
	private String receiverNickname;
	private String receiverImg;
	private int unreadCount;
}
