package com.huterox.whitehole.whiteholemessage.controller.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.QuizAnsTipsMsgService;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsTipsQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/quiz")
public class QuizAnsTipsController {

    @Autowired
    QuizAnsTipsMsgService quizAnsTipsMsgService;

    @PostMapping("/quizAnsTips")
    public R quizAnsTips(@RequestBody QuizAnsTipsQ quizAnsTipsQ){
        return quizAnsTipsMsgService.ansTipsMsg(quizAnsTipsQ);
    }


    @PostMapping("/quizAnsTipsLoadAll")
    public R quizAnsTipsLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return quizAnsTipsMsgService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/quizAnsTipsLoadNRead")
    public R quizAnsTipsLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return quizAnsTipsMsgService.LoadNReadMsg(loadMessageQ);
    }
}
