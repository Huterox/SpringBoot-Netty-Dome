package com.huterox.whitehole.whiteholemessage.Enum;

import lombok.Data;


public enum MessageActionEnum {

    //定义消息类型

    CONNECT(1,"第一次（或重连）初始化连接"),
    CHAT(2,"聊天消息"),
    SIGNED(3,"消息签收"),
    BLOG_COMMENT(7,"博文评论消息"),
    BLOG_THUMB(10,"博文点赞消息"),
    ANS_THUMB(11,"回答点赞消息"),
    QUIZANS_COMMENT(8,"回答评论消息"),
    QUIZANS_TIPS(12,"提问回答消息"),
    BLOG_PULL(9,"博文pull"),
    KEEPALIVE(4,"客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友"),
    HOLEADUITMSG(6,"审核消息");



    public final Integer type;
    public final String content;
    MessageActionEnum(Integer type,String content) {
        this.type = type;
        this.content = content;
    }

}
