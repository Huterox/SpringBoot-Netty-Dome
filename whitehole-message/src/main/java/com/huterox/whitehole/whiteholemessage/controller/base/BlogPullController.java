package com.huterox.whitehole.whiteholemessage.controller.base;

import java.util.Arrays;
import java.util.Map;

import com.huterox.whitehole.whiteholemessage.entity.base.BlogPullEntity;
import com.huterox.whitehole.whiteholemessage.service.base.BlogPullService;
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
@RequestMapping("whiteholemessage/blogpull")
public class BlogPullController {
    @Autowired
    private BlogPullService blogPullService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = blogPullService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{msgid}")
    public R info(@PathVariable("msgid") Long msgid){
		BlogPullEntity blogPull = blogPullService.getById(msgid);

        return R.ok().put("blogPull", blogPull);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BlogPullEntity blogPull){
		blogPullService.save(blogPull);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BlogPullEntity blogPull){
		blogPullService.updateById(blogPull);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] msgids){
		blogPullService.removeByIds(Arrays.asList(msgids));

        return R.ok();
    }

}
