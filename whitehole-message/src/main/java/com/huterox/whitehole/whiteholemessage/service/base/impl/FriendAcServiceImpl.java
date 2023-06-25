package com.huterox.whitehole.whiteholemessage.service.base.impl;

import com.huterox.whitehole.whiteholemessage.entity.base.FriendAcEntity;
import com.huterox.whitehole.whiteholemessage.service.base.FriendAcService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholemessage.dao.FriendAcDao;



@Service("friendAcService")
public class FriendAcServiceImpl extends ServiceImpl<FriendAcDao, FriendAcEntity> implements FriendAcService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendAcEntity> page = this.page(
                new Query<FriendAcEntity>().getPage(params),
                new QueryWrapper<FriendAcEntity>()
        );

        return new PageUtils(page);
    }

}