package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.Enum.MessageActionEnum;
import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.entity.base.QuizAnsEntity;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageR;
import com.huterox.whitehole.whiteholemessage.service.base.QuizAnsService;
import com.huterox.whitehole.whiteholemessage.service.surface.AnsThumbService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import com.huterox.whitehole.whiteholemessage.utils.RedisTransKey;
import com.huterox.whitehole.whiteholemessage.utils.RedisUtils;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsMsgQ;
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
public class AnsThumbServiceImpl implements AnsThumbService {
    
    @Autowired
    QuizAnsService quizAnsService;

    @Autowired
    RedisUtils redisUtils;

    @Value("${loadMessage.limit}")
    Integer limitTime;
    
    @Override
    public R AnsMsgThumb(QuizAnsMsgQ quizAnsMsgQ) {
        //1.对消息进行存储,只要用户在线的话，我们就直接先给他签收一下
        String userid = quizAnsMsgQ.getUserid();
        Channel channel = UserConnectPool.getChannelFromMap(userid);
        QuizAnsEntity quizAnsEntity = new QuizAnsEntity();
        BeanUtils.copyProperties(quizAnsMsgQ,quizAnsEntity);
        quizAnsEntity.setCreatTime(DateUtils.getCurrentTime());
        quizAnsEntity.setStatus(1);
        quizAnsService.save(quizAnsEntity);
        if(channel!=null){

            //我们这边直接转发消息就好了，不需要再额外处理
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            JsonUtils.objectToJson(
                                    Objects.requireNonNull(R.ok().put("data", quizAnsEntity))
                                            .put("type", MessageActionEnum.ANS_THUMB.type)
                            )
                    )
            );
        }else {
            quizAnsEntity.setStatus(2);
            quizAnsService.updateById(quizAnsEntity);
        }

        return R.ok();
    }

    /**
     * 负责加载消息，此外加载全部的接口，统一+20分钟限制
     * */

    @Override
    public R LoadAllMsg(LoadMessageQ loadMessageQ) {

        String userid = loadMessageQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getAnsThumbLoadKey(userid))){
            LoadMessageR loadMessageR = new LoadMessageR();
            return R.ok().put("msg",loadMessageR);
        }
        R read = read(loadMessageQ, 1);
        redisUtils.set(RedisTransKey.setAnsThumbLoadKey(userid),1,limitTime, TimeUnit.MINUTES);
        return read;
    }

    @Override
    public R LoadNReadMsg(LoadMessageQ loadMessageQ) {
        return read(loadMessageQ,2);
    }

    private R read(LoadMessageQ loadMessageQ, int mode){
        String userid = loadMessageQ.getUserid();
        int max = loadMessageQ.getMax();

        QueryWrapper<QuizAnsEntity> ansEntityQueryWrapper = new QueryWrapper<>();
        ansEntityQueryWrapper.eq("userid",userid);
        if(mode==2){
            ansEntityQueryWrapper.eq("status",2);
        }
        ansEntityQueryWrapper.orderByDesc("msgid");
        ansEntityQueryWrapper.last("limit 0,"+max);
        List<QuizAnsEntity> list = quizAnsService.list(ansEntityQueryWrapper);
        for (QuizAnsEntity quizAnsEntity:list){
            quizAnsEntity.setStatus(1);
        }
        //在批量更新消息
        quizAnsService.updateBatchById(list);
        LoadMessageR loadMessageR = new LoadMessageR();
        loadMessageR.setList(list);
        loadMessageR.setSize(list.size());
        return R.ok().put("msg",loadMessageR);
    }
}
