package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsMsgQ;

public interface AnsThumbService {

    R AnsMsgThumb(QuizAnsMsgQ quizAnsMsgQ);

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);

}
