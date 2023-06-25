package com.huterox.whitehole.whiteholemessage.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.BlogCommentMsgService;
import com.huterox.whiteholecould.entity.message.Q.BlogCommentMsgQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/blog")
public class BlogCommentMsgController {

    @Autowired
    BlogCommentMsgService blogCommentMsgService;

    @PostMapping("/blogComment")
    public R blogComment(@RequestBody BlogCommentMsgQ blogCommentMsgQ){
        return blogCommentMsgService.blogCommentMsg(blogCommentMsgQ);
    }

    @PostMapping("/blogCommentLoadAll")
    public R blogCommentLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return blogCommentMsgService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/blogCommentLoadNRead")
    public R blogCommentLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return blogCommentMsgService.LoadNReadMsg(loadMessageQ);
    }
}
