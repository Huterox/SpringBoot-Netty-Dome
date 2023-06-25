package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.Enum.MessageActionEnum;
import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.entity.base.HoleAuditEntity;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageR;
import com.huterox.whitehole.whiteholemessage.service.base.HoleAuditService;
import com.huterox.whitehole.whiteholemessage.service.surface.HoleAduitMsgService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import com.huterox.whitehole.whiteholemessage.utils.RedisTransKey;
import com.huterox.whitehole.whiteholemessage.utils.RedisUtils;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
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
public class HoleAduitMsgServiceImpl implements HoleAduitMsgService {

    @Autowired
    HoleAuditService auditService;

    @Autowired
    RedisUtils redisUtils;

    @Value("${loadMessage.limit}")
    Integer limitTime;

    @Override
    public R holeaduitMsg(HoleAduitMsgQ holeAduitMsgQ) {
        //1.对消息进行存储,只要用户在线的话，我们就直接先给他签收一下
        String userid = holeAduitMsgQ.getUserid();
        Channel channel = UserConnectPool.getChannelFromMap(userid);
        HoleAuditEntity holeAuditEntity = new HoleAuditEntity();
        BeanUtils.copyProperties(holeAduitMsgQ,holeAuditEntity);
        holeAuditEntity.setCreateTime(DateUtils.getCurrentTime());
        holeAuditEntity.setStatus(1);
        auditService.save(holeAuditEntity);
        if(channel!=null){
            
            //我们这边直接转发消息就好了，不需要再额外处理
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            JsonUtils.objectToJson(
                                    Objects.requireNonNull(R.ok().put("data", holeAuditEntity))
                                            .put("type", MessageActionEnum.HOLEADUITMSG.type)
                            )
                    )
            );
        }else {
            holeAuditEntity.setStatus(2);
            auditService.updateById(holeAuditEntity);
        }
        
        return R.ok();
    }

    @Override
    public R holeaduitMsgSingle() {
        /**
         * 我们做出如下约定：
         *  1.只要用户在线就给他签收
         *  2.我们客户端调用消息接受接口是在用户离线之后创建的
         *  3.客户端需要将本地的消息存储7天，客户端的读取状态是由客户端决定的
         *  4.服务端只保证，消息是否发送，签收（我们认为只要在线就可以签收）(1-已签收，2-未签收)
         * */

        return R.ok();
    }

    @Override
    public R LoadAllMsg(LoadMessageQ loadMessageQ) {

        String userid = loadMessageQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getAduitLoadKey(userid))){
            LoadMessageR loadMessageR = new LoadMessageR();
            return R.ok().put("msg",loadMessageR);
        }
        R read = read(loadMessageQ, 1);
        redisUtils.set(RedisTransKey.setAduitLoadKey(userid),1,limitTime, TimeUnit.MINUTES);
        return read;
    }

    @Override
    public R LoadNReadMsg(LoadMessageQ loadMessageQ) {
        return read(loadMessageQ,2);
    }

    private R read(LoadMessageQ loadMessageQ, int mode){
        String userid = loadMessageQ.getUserid();
        int max = loadMessageQ.getMax();

        QueryWrapper<HoleAuditEntity> holeAuditEntityWrapper = new QueryWrapper<>();
        holeAuditEntityWrapper.eq("userid",userid);
        if(mode==2){
            holeAuditEntityWrapper.eq("status",2);
        }
        holeAuditEntityWrapper.orderByDesc("msgid");
        holeAuditEntityWrapper.last("limit 0,"+max);
        List<HoleAuditEntity> list = auditService.list(holeAuditEntityWrapper);
        for (HoleAuditEntity holeAuditEntity:list){
            holeAuditEntity.setStatus(1);
        }
        //在批量更新消息
        auditService.updateBatchById(list);
        LoadMessageR loadMessageR = new LoadMessageR();
        loadMessageR.setList(list);
        loadMessageR.setSize(list.size());
        return R.ok().put("msg",loadMessageR);
    }
}
