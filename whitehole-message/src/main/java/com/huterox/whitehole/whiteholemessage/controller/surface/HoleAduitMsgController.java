package com.huterox.whitehole.whiteholemessage.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.HoleAduitMsgService;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/holeAduit")
public class HoleAduitMsgController {

    @Autowired
    HoleAduitMsgService holeAduitMsgService;

    @PostMapping("/aduit")
    public R holeAduitMsg(@Validated @RequestBody HoleAduitMsgQ holeAduitMsgQ){
        return holeAduitMsgService.holeaduitMsg(holeAduitMsgQ);
    }

    @PostMapping("/aduitLoadAll")
    public R aduitLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return holeAduitMsgService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/aduitLoadNRead")
    public R aduitLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return holeAduitMsgService.LoadNReadMsg(loadMessageQ);
    }
}
