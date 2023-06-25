package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.BlogCommentEntity;
import com.huterox.whitehole.whiteholemessage.service.base.BlogCommentService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.BlogCommentDao;



@Service("blogCommentService")
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentDao, BlogCommentEntity> implements BlogCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogCommentEntity> page = this.page(
                new Query<BlogCommentEntity>().getPage(params),
                new QueryWrapper<BlogCommentEntity>()
        );

        return new PageUtils(page);
    }

}