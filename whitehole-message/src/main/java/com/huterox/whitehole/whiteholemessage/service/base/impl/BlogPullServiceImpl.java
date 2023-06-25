package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.BlogPullEntity;
import com.huterox.whitehole.whiteholemessage.service.base.BlogPullService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.BlogPullDao;



@Service("blogPullService")
public class BlogPullServiceImpl extends ServiceImpl<BlogPullDao, BlogPullEntity> implements BlogPullService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogPullEntity> page = this.page(
                new Query<BlogPullEntity>().getPage(params),
                new QueryWrapper<BlogPullEntity>()
        );

        return new PageUtils(page);
    }

}