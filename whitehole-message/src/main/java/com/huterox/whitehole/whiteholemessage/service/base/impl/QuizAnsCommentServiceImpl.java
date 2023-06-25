package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.QuizAnsCommentEntity;
import com.huterox.whitehole.whiteholemessage.service.base.QuizAnsCommentService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.QuizAnsCommentDao;


@Service("quizAnsCommentService")
public class QuizAnsCommentServiceImpl extends ServiceImpl<QuizAnsCommentDao, QuizAnsCommentEntity> implements QuizAnsCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuizAnsCommentEntity> page = this.page(
                new Query<QuizAnsCommentEntity>().getPage(params),
                new QueryWrapper<QuizAnsCommentEntity>()
        );

        return new PageUtils(page);
    }

}