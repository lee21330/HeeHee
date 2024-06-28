package com.shinhan.heehee.dto.request;

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
public class ChatImageDTO {
    private int imgSeq;
    private int tablePk;
    private String imgName;
    private int msgSeq;
    private String userId;
}
