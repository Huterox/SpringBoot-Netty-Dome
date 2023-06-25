package com.huterox.whitehole.whiteholemessage.controller.base;

import java.util.Arrays;
import java.util.Map;

import com.huterox.whitehole.whiteholemessage.entity.base.BlogCommentEntity;
import com.huterox.whitehole.whiteholemessage.service.base.BlogCommentService;
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
@RequestMapping("whiteholemessage/blogcomment")
public class BlogCommentController {
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = blogCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{msgid}")
    public R info(@PathVariable("msgid") Long msgid){
		BlogCommentEntity blogComment = blogCommentService.getById(msgid);

        return R.ok().put("blogComment", blogComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BlogCommentEntity blogComment){
		blogCommentService.save(blogComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BlogCommentEntity blogComment){
		blogCommentService.updateById(blogComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] msgids){
		blogCommentService.removeByIds(Arrays.asList(msgids));

        return R.ok();
    }

}
