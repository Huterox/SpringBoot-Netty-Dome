package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.QuizAnsEntity;
import com.huterox.whitehole.whiteholemessage.service.base.QuizAnsService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.QuizAnsDao;



@Service("quizAnsService")
public class QuizAnsServiceImpl extends ServiceImpl<QuizAnsDao, QuizAnsEntity> implements QuizAnsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuizAnsEntity> page = this.page(
                new Query<QuizAnsEntity>().getPage(params),
                new QueryWrapper<QuizAnsEntity>()
        );

        return new PageUtils(page);
    }

}