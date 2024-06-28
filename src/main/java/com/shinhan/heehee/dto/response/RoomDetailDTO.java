package com.shinhan.heehee.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetailDTO {
	RoomProductDTO roomProductDTO;
	RoomUserDTO roomUserDTO;
	List<RoomMessageDTO> roomMessageDTO;
}
