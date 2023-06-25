package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.entity.surface.chatMsg.ChatMsgQ;
import com.huterox.whitehole.whiteholemessage.service.surface.ChatService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Override
    public void ChatTOFriend(ChatMsgQ chatMsgQ) {
        //这里开始进行聊，接受到需要进行聊天消息的请求
        //将该请求，通过nutty进行转发。

        String senderId = chatMsgQ.getSenderId();
        //发送消息
        Channel receiverChannel = UserConnectPool.getChannelFromMap(senderId);
        if(receiverChannel==null){
            /**
             * 用户不在线,此时将消息标记为未读
             * */
        }else {
            //确定用户组里面存不存在，双层保险
            Channel findChannel = UserConnectPool.getChannelGroup().find(receiverChannel.id());

            if(findChannel!=null){
                receiverChannel.writeAndFlush(
                        new TextWebSocketFrame(
                                JsonUtils.objectToJson(chatMsgQ)
                        )
                );
            }else {
                /**
                 * 此时说明用户确实不在
                 * */
            }
        }

    }

    @Override
    public void SignChatMsg() {
        //聊天消息的签名，是否收到
    }


}
