package com.huterox.whitehole.whiteholemessage.controller.surface;


import com.huterox.whitehole.whiteholemessage.Bean.DataContent;
import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnAllQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.ann.AnnToOneQ;
import com.huterox.whitehole.whiteholemessage.service.surface.AnnouncementMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/ann")
public class AnnouncementMsgController {

    /**这个其实是给我们的这个后台管理员用的接口
     * Controller
     * */

    @Autowired
    AnnouncementMsgService announcementMsgService;

    @RequestMapping("/AnnOne")
    public void  AnnOne(@Validated @RequestBody AnnToOneQ annToOneQ){
        announcementMsgService.AnnouncementMsgToOne(annToOneQ);
    }
    @RequestMapping("/AnnAll")
    public void  AnnAll(@Validated @RequestBody AnnAllQ annAllQ){
        announcementMsgService.AnnouncementMsgToAll(annAllQ);
    }
}
