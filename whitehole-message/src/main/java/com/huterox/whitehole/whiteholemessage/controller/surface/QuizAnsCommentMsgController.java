package com.huterox.whitehole.whiteholemessage.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.QuizAnsCommentMsgService;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsCommentMsgQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/quiz")
public class QuizAnsCommentMsgController {

    @Autowired
    QuizAnsCommentMsgService quizAnsCommentMsgService;

    @PostMapping("/quizAnsComment")
    public R quizAnsComment(@RequestBody QuizAnsCommentMsgQ ansCommentMsgQ){
        return quizAnsCommentMsgService.quizAnsCommentMsg(ansCommentMsgQ);
    }

    @PostMapping("/quizAnsCommentLoadAll")
    public R quizAnsCommentLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return quizAnsCommentMsgService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/quizAnsCommentLoadNRead")
    public R quizAnsCommentLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return quizAnsCommentMsgService.LoadNReadMsg(loadMessageQ);
    }

}
