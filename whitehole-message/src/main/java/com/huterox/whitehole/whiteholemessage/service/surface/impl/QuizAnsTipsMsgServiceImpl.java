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
import com.huterox.whitehole.whiteholemessage.service.surface.QuizAnsTipsMsgService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import com.huterox.whitehole.whiteholemessage.utils.RedisTransKey;
import com.huterox.whitehole.whiteholemessage.utils.RedisUtils;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsTipsQ;
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
public class QuizAnsTipsMsgServiceImpl implements QuizAnsTipsMsgService {

    @Autowired
    QuizAnsService quizAnsService;

    @Autowired
    RedisUtils redisUtils;

    @Value("${loadMessage.limit}")
    Integer limitTime;

    @Override
    public R ansTipsMsg(QuizAnsTipsQ quizTipsQ) {
        String userid = quizTipsQ.getUserid();
        Channel channel = UserConnectPool.getChannelFromMap(userid);
        QuizAnsEntity quizAnsEntity = new QuizAnsEntity();
        BeanUtils.copyProperties(quizTipsQ,quizAnsEntity);
        quizAnsEntity.setCreatTime(DateUtils.getCurrentTime());
        quizAnsEntity.setStatus(1);
        quizAnsService.save(quizAnsEntity);
        if(channel!=null){

            //我们这边直接转发消息就好了，不需要再额外处理
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            JsonUtils.objectToJson(
                                    Objects.requireNonNull(R.ok().put("data", quizAnsEntity))
                                            .put("type", MessageActionEnum.QUIZANS_TIPS.type)
                            )
                    )
            );
            
        }else {
            //状态为2表示当前用户可能由于什么原因没有和我们的消息服务器连接
            quizAnsEntity.setStatus(2);
            quizAnsService.updateById(quizAnsEntity);
        }
        
        return R.ok();
    }

    @Override
    public R LoadAllMsg(LoadMessageQ loadMessageQ) {

        String userid = loadMessageQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getAnsTipsLoadKey(userid))){
            LoadMessageR loadMessageR = new LoadMessageR();
            return R.ok().put("msg",loadMessageR);
        }
        R read = read(loadMessageQ, 1);
        redisUtils.set(RedisTransKey.setAnsTipsLoadKey(userid),1,limitTime, TimeUnit.MINUTES);
        return read;
    }

    @Override
    public R LoadNReadMsg(LoadMessageQ loadMessageQ) {
        return read(loadMessageQ,2);
    }

    private R read(LoadMessageQ loadMessageQ, int mode){
        String userid = loadMessageQ.getUserid();
        int max = loadMessageQ.getMax();

        QueryWrapper<QuizAnsEntity> quizAnsEntityWrapper = new QueryWrapper<>();
        quizAnsEntityWrapper.eq("userid",userid);
        if(mode==2){
            quizAnsEntityWrapper.eq("status",2);
        }
        quizAnsEntityWrapper.orderByDesc("msgid");
        quizAnsEntityWrapper.last("limit 0,"+max);
        List<QuizAnsEntity> list = quizAnsService.list(quizAnsEntityWrapper);
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
