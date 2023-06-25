package com.huterox.whitehole.whiteholemessage.controller.base;

import java.util.Arrays;
import java.util.Map;

import com.huterox.whitehole.whiteholemessage.entity.base.FriendAcEntity;
import com.huterox.whitehole.whiteholemessage.service.base.FriendAcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-10-27 13:56:13
 */
@RestController
@RequestMapping("whiteholemessage/friendac")
public class FriendAcController {
    @Autowired
    private FriendAcService friendAcService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendAcService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{msgid}")
    public R info(@PathVariable("msgid") Long msgid){
		FriendAcEntity friendAc = friendAcService.getById(msgid);

        return R.ok().put("friendAc", friendAc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FriendAcEntity friendAc){
		friendAcService.save(friendAc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FriendAcEntity friendAc){
		friendAcService.updateById(friendAc);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] msgids){
		friendAcService.removeByIds(Arrays.asList(msgids));

        return R.ok();
    }

}
