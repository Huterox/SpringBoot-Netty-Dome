package com.huterox.whitehole.whiteholemessage.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;

public interface HoleAduitMsgService {

    //负责处理转发审核消息
    public R holeaduitMsg(HoleAduitMsgQ holeAduitMsgQ);

    //负责对消息进行签收
    @Deprecated
    public R holeaduitMsgSingle();

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);
}
