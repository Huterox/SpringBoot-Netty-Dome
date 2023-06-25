package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.BlogPullMsgQ;

public interface BlogPullMsgService {

    public R blogPullMsg(BlogPullMsgQ blogPullMsgQ);

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);
}
