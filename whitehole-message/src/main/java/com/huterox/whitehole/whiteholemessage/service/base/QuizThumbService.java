package com.huterox.whitehole.whiteholemessage.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholemessage.entity.base.QuizThumbEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-10-27 13:56:13
 */
public interface QuizThumbService extends IService<QuizThumbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

