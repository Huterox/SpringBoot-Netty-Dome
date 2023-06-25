package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnAllQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnToOneQ;

public interface AnnouncementMsgService {

    /**
     * 推送给指定用户
     */
    void AnnouncementMsgToOne(AnnToOneQ annToOneQ);

    /**
     * 推送给所有用户
     */
    void AnnouncementMsgToAll(AnnAllQ allQ);

}