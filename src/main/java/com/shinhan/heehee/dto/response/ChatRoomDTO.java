package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
	private int id;
	private String lastContent;
	private String sendTime;
	private String receiverId;
	private String receiverNickname;
	private String receiverImg;
	private int unreadCount;
	private int maxMessageNo;
}
