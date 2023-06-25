package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.HoleActivityEntity;
import com.huterox.whitehole.whiteholemessage.service.base.HoleActivityService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.HoleActivityDao;



@Service("holeActivityService")
public class HoleActivityServiceImpl extends ServiceImpl<HoleActivityDao, HoleActivityEntity> implements HoleActivityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HoleActivityEntity> page = this.page(
                new Query<HoleActivityEntity>().getPage(params),
                new QueryWrapper<HoleActivityEntity>()
        );

        return new PageUtils(page);
    }

}