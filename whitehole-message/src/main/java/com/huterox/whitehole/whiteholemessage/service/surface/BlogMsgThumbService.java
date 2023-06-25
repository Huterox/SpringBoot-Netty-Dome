package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.BlogThumbQ;

public interface BlogMsgThumbService {

    R blogMsgThumb(BlogThumbQ blogThumbQ);

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);

}
