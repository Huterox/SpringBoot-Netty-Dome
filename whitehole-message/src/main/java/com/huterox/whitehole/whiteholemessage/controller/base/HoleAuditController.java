package com.huterox.whitehole.whiteholemessage.controller.base;

import java.util.Arrays;
import java.util.Map;


import com.huterox.whitehole.whiteholemessage.entity.base.HoleAuditEntity;
import com.huterox.whitehole.whiteholemessage.service.base.HoleAuditService;
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
@RequestMapping("whiteholemessage/holeaudit")
public class HoleAuditController {
    @Autowired
    private HoleAuditService holeAuditService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = holeAuditService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{msgid}")
    public R info(@PathVariable("msgid") Long msgid){
		HoleAuditEntity holeAudit = holeAuditService.getById(msgid);

        return R.ok().put("holeAudit", holeAudit);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HoleAuditEntity holeAudit){
		holeAuditService.save(holeAudit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody HoleAuditEntity holeAudit){
		holeAuditService.updateById(holeAudit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] msgids){
		holeAuditService.removeByIds(Arrays.asList(msgids));
        return R.ok();
    }

}
