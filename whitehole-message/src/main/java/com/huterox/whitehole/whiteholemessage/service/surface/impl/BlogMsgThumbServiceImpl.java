package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.Enum.MessageActionEnum;
import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.entity.base.BlogThumbEntity;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageR;
import com.huterox.whitehole.whiteholemessage.service.base.BlogThumbService;
import com.huterox.whitehole.whiteholemessage.service.surface.BlogMsgThumbService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import com.huterox.whitehole.whiteholemessage.utils.RedisTransKey;
import com.huterox.whitehole.whiteholemessage.utils.RedisUtils;
import com.huterox.whiteholecould.entity.message.Q.BlogThumbQ;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class BlogMsgThumbServiceImpl implements BlogMsgThumbService {
    
    @Autowired
    BlogThumbService blogThumbService;

    @Autowired
    RedisUtils redisUtils;

    @Value("${loadMessage.limit}")
    Integer limitTime;
    
    @Override
    public R blogMsgThumb(BlogThumbQ blogThumbQ) {
        //1.对消息进行存储,只要用户在线的话，我们就直接先给他签收一下
        String userid = blogThumbQ.getUserid();
        Channel channel = UserConnectPool.getChannelFromMap(userid);
        BlogThumbEntity blogThumbEntity = new BlogThumbEntity();
        BeanUtils.copyProperties(blogThumbQ,blogThumbEntity);
        blogThumbEntity.setCreatTime(DateUtils.getCurrentTime());
        blogThumbEntity.setStatus(1);
        blogThumbService.save(blogThumbEntity);
        if(channel!=null){
            
            //我们这边直接转发消息就好了，不需要再额外处理
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            JsonUtils.objectToJson(
                                    Objects.requireNonNull(R.ok().put("data", blogThumbEntity))
                                            .put("type", MessageActionEnum.BLOG_THUMB.type)
                            )
                    )
            );
        }else {
            blogThumbEntity.setStatus(2);
            blogThumbService.updateById(blogThumbEntity);
        }
        
        return R.ok();
    }

    @Override
    public R LoadAllMsg(LoadMessageQ loadMessageQ) {

        String userid = loadMessageQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getBlogThumbLoadKey(userid))){
            LoadMessageR loadMessageR = new LoadMessageR();
            return R.ok().put("msg",loadMessageR);
        }
        R read = read(loadMessageQ, 1);
        redisUtils.set(RedisTransKey.setBlogThumbLoadKey(userid),1,limitTime, TimeUnit.MINUTES);
        return read;
    }

    @Override
    public R LoadNReadMsg(LoadMessageQ loadMessageQ) {
        return read(loadMessageQ,2);
    }

    private R read(LoadMessageQ loadMessageQ, int mode){
        String userid = loadMessageQ.getUserid();
        int max = loadMessageQ.getMax();

        QueryWrapper<BlogThumbEntity> blogThumbEntityWrapper = new QueryWrapper<>();
        blogThumbEntityWrapper.eq("userid",userid);
        if(mode==2){
            blogThumbEntityWrapper.eq("status",2);
        }
        blogThumbEntityWrapper.orderByDesc("msgid");
        blogThumbEntityWrapper.last("limit 0,"+max);
        List<BlogThumbEntity> list = blogThumbService.list(blogThumbEntityWrapper);
        for (BlogThumbEntity blogThumbEntity:list){
            blogThumbEntity.setStatus(1);
        }
        //在批量更新消息
        blogThumbService.updateBatchById(list);
        LoadMessageR loadMessageR = new LoadMessageR();
        loadMessageR.setList(list);
        loadMessageR.setSize(list.size());
        return R.ok().put("msg",loadMessageR);
    }
}
