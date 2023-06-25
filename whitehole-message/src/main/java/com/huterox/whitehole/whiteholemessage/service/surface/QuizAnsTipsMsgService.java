package com.huterox.whitehole.whiteholemessage.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsTipsQ;

/**
 * 提问有回答的消息转发
 * */
public interface QuizAnsTipsMsgService {

    R ansTipsMsg(QuizAnsTipsQ quizTipsQ);

    R LoadAllMsg(LoadMessageQ loadMessageQ);

    R LoadNReadMsg(LoadMessageQ loadMessageQ);
}
