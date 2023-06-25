package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.huterox.common.utils.DateUtils;
import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnAllQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnToOneEntity;
import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnToOneQ;
import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.service.surface.AnnouncementMsgService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnnouncementMsgServiceImpl implements AnnouncementMsgService {

    @Override
    public void AnnouncementMsgToOne(AnnToOneQ annToOneQ) {
        String toUserid = annToOneQ.getToUserid();
        Channel channel = UserConnectPool.getChannelFromMap(toUserid);
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }
        AnnToOneEntity annToOneEntity = new AnnToOneEntity();
        annToOneEntity.setMsg(annToOneQ.getMsg());
        annToOneEntity.setCreateTime(DateUtils.getCurrentTime());
        channel.writeAndFlush(
                new TextWebSocketFrame(
                        JsonUtils.objectToJson(annToOneEntity)
                )
        );
    }


    @Override
    public void AnnouncementMsgToAll(AnnAllQ annAllQ) {
        String msg = annAllQ.getMsg();
        UserConnectPool.getChannelGroup().writeAndFlush(
                new TextWebSocketFrame(
                        JsonUtils.objectToJson(msg)
                )
        );
    }
}