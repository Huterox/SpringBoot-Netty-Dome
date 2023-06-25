package com.huterox.whitehole.whiteholemessage.controller.base;

import java.util.Arrays;
import java.util.Map;

import com.huterox.whitehole.whiteholemessage.entity.base.HoleActivityEntity;
import com.huterox.whitehole.whiteholemessage.service.base.HoleActivityService;
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
@RequestMapping("whiteholemessage/holeactivity")
public class HoleActivityController {
    @Autowired
    private HoleActivityService holeActivityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = holeActivityService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{msgid}")
    public R info(@PathVariable("msgid") Long msgid){
		HoleActivityEntity holeActivity = holeActivityService.getById(msgid);

        return R.ok().put("holeActivity", holeActivity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HoleActivityEntity holeActivity){
		holeActivityService.save(holeActivity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody HoleActivityEntity holeActivity){
		holeActivityService.updateById(holeActivity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] msgids){
		holeActivityService.removeByIds(Arrays.asList(msgids));

        return R.ok();
    }

}
