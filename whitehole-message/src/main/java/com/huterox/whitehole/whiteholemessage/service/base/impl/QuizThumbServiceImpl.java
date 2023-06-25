package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.QuizThumbEntity;
import com.huterox.whitehole.whiteholemessage.service.base.QuizThumbService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.QuizThumbDao;



@Service("quizThumbService")
public class QuizThumbServiceImpl extends ServiceImpl<QuizThumbDao, QuizThumbEntity> implements QuizThumbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuizThumbEntity> page = this.page(
                new Query<QuizThumbEntity>().getPage(params),
                new QueryWrapper<QuizThumbEntity>()
        );

        return new PageUtils(page);
    }

}