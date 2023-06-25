package com.huterox.whitehole.whiteholemessage.service.surface;

import com.huterox.common.utils.R;

import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsCommentMsgQ;

public interface QuizAnsCommentMsgService {

    public R quizAnsCommentMsg(QuizAnsCommentMsgQ ansCommentMsgQ);

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);
}
