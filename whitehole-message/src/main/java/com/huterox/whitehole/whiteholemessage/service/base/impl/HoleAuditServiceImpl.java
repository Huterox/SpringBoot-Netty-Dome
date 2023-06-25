package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.HoleAuditEntity;
import com.huterox.whitehole.whiteholemessage.service.base.HoleAuditService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.HoleAuditDao;


@Service("holeAuditService")
public class HoleAuditServiceImpl extends ServiceImpl<HoleAuditDao, HoleAuditEntity> implements HoleAuditService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HoleAuditEntity> page = this.page(
                new Query<HoleAuditEntity>().getPage(params),
                new QueryWrapper<HoleAuditEntity>()
        );

        return new PageUtils(page);
    }

}