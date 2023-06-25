package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.BlogThumbEntity;
import com.huterox.whitehole.whiteholemessage.service.base.BlogThumbService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.BlogThumbDao;


@Service("blogThumbService")
public class BlogThumbServiceImpl extends ServiceImpl<BlogThumbDao, BlogThumbEntity> implements BlogThumbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogThumbEntity> page = this.page(
                new Query<BlogThumbEntity>().getPage(params),
                new QueryWrapper<BlogThumbEntity>()
        );

        return new PageUtils(page);
    }

}