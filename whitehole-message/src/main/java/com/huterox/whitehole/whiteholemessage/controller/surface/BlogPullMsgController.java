package com.huterox.whitehole.whiteholemessage.controller.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.service.surface.BlogPullMsgService;
import com.huterox.whiteholecould.entity.message.Q.BlogPullMsgQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/blog")
public class BlogPullMsgController {

    @Autowired
    BlogPullMsgService blogPullMsgService;

    @PostMapping("/blogPull")
    public R blogPullMsgController(@RequestBody BlogPullMsgQ blogPullMsgQ){

        return blogPullMsgService.blogPullMsg(blogPullMsgQ);

    }

    @PostMapping("/blogPullLoadAll")
    public R blogPullLoadAll(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return blogPullMsgService.LoadAllMsg(loadMessageQ);
    }

    @PostMapping("/blogPullLoadNRead")
    public R blogPullLoadNRead(@Validated @RequestBody LoadMessageQ loadMessageQ){
        return blogPullMsgService.LoadNReadMsg(loadMessageQ);
    }

}
