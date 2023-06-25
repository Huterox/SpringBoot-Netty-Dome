package com.huterox.whitehole.whiteholemessage.controller.surface;


import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.AnsThumbService;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsMsgQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/quiz/ans")
public class AnsThumbMsgController {

    @Autowired
    AnsThumbService ansThumbService;

    @PostMapping("/thumb")
    public R blogMsgThumb(@Validated @RequestBody QuizAnsMsgQ quizAnsMsgQ){
        return ansThumbService.AnsMsgThumb(quizAnsMsgQ);
    }

    @PostMapping("/thumbLoadAll")
    @NeedLogin
    public R thumbLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return ansThumbService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/thumbLoadNRead")
    @NeedLogin
    public R thumbLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return ansThumbService.LoadNReadMsg(loadMessageQ);
    }


}
