package com.huterox.whitehole.whiteholemessage.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.BlogMsgThumbService;
import com.huterox.whiteholecould.entity.message.Q.BlogThumbQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/blog")
public class BlogthumbMsgController {
    @Autowired
    BlogMsgThumbService blogMsgThumbService;

    @PostMapping("/thumb")
    public R blogMsgThumb(@Validated @RequestBody BlogThumbQ blogThumbQ){
        return blogMsgThumbService.blogMsgThumb(blogThumbQ);
    }

    @PostMapping("/thumbLoadAll")
    public R thumbLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return blogMsgThumbService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/thumbLoadNRead")
    public R thumbLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return blogMsgThumbService.LoadNReadMsg(loadMessageQ);
    }
}
