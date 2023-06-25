package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.BlogCommentMsgQ;

public interface BlogCommentMsgService {

    //负责对博文的评论消息进行一个转发处理
    public R blogCommentMsg(BlogCommentMsgQ blogCommentMsgQ);


    @Deprecated
    public R holeaduitMsgSingle();

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);
}
