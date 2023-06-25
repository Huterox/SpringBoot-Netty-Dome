package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.whitehole.whiteholemessage.entity.surface.chatMsg.ChatMsgQ;

public interface ChatService {
    public void ChatTOFriend(ChatMsgQ chatMsgQ);
    public void SignChatMsg();
}
