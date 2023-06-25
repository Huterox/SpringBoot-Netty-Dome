package com.huterox.whitehole.whiteholemessage.entity.surface.chatMsg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsgQ implements Serializable {
    private String senderId;
    private String receiverId;
    private String msg;
    private String msgId;
}
