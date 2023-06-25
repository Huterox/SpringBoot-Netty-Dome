package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.Enum.MessageActionEnum;
import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.entity.base.QuizAnsCommentEntity;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageQ;
import com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage.LoadMessageR;
import com.huterox.whitehole.whiteholemessage.service.base.QuizAnsCommentService;
import com.huterox.whitehole.whiteholemessage.service.surface.QuizAnsCommentMsgService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import com.huterox.whitehole.whiteholemessage.utils.RedisTransKey;
import com.huterox.whitehole.whiteholemessage.utils.RedisUtils;
import com.huterox.whiteholecould.entity.message.Q.QuizAnsCommentMsgQ;
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
public class QuizAnsCommentMsgServiceImpl implements QuizAnsCommentMsgService {
    
    @Autowired
    QuizAnsCommentService quizAnsCommentService;

    @Autowired
    RedisUtils redisUtils;

    @Value("${loadMessage.limit}")
    Integer limitTime;
    
    @Override
    public R quizAnsCommentMsg(QuizAnsCommentMsgQ ansCommentMsgQ) {
        String userid = ansCommentMsgQ.getUserid();
        Channel channel = UserConnectPool.getChannelFromMap(userid);
        QuizAnsCommentEntity quizAnsCommentEntity = new QuizAnsCommentEntity();
        BeanUtils.copyProperties(ansCommentMsgQ,quizAnsCommentEntity);
        quizAnsCommentEntity.setCreatTime(DateUtils.getCurrentTime());
        quizAnsCommentEntity.setStatus(1);
        quizAnsCommentService.save(quizAnsCommentEntity);
        if(channel!=null){

            //我们这边直接转发消息就好了，不需要再额外处理
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            JsonUtils.objectToJson(
                                    Objects.requireNonNull(R.ok().put("data", quizAnsCommentEntity))
                                            .put("type", MessageActionEnum.QUIZANS_COMMENT.type)
                            )
                    )
            );
            
        }else {
            //状态为2表示当前用户可能由于什么原因没有和我们的消息服务器连接
            quizAnsCommentEntity.setStatus(2);
            quizAnsCommentService.updateById(quizAnsCommentEntity);
        }

        
        return R.ok();
    }

    @Override
    public R LoadAllMsg(LoadMessageQ loadMessageQ) {

        String userid = loadMessageQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getAnsCommentLoadKey(userid))){
            LoadMessageR loadMessageR = new LoadMessageR();
            return R.ok().put("msg",loadMessageR);
        }
        R read = read(loadMessageQ, 1);
        redisUtils.set(RedisTransKey.setAnsCommentLoadKey(userid),1,limitTime, TimeUnit.MINUTES);
        return read;
    }

    @Override
    public R LoadNReadMsg(LoadMessageQ loadMessageQ) {
        return read(loadMessageQ,2);
    }

    private R read(LoadMessageQ loadMessageQ, int mode){
        String userid = loadMessageQ.getUserid();
        int max = loadMessageQ.getMax();

        QueryWrapper<QuizAnsCommentEntity> quizAnsCommentEntityWrapper = new QueryWrapper<>();
        quizAnsCommentEntityWrapper.eq("userid",userid);
        if(mode==2){
            quizAnsCommentEntityWrapper.eq("status",2);
        }
        quizAnsCommentEntityWrapper.orderByDesc("msgid");
        quizAnsCommentEntityWrapper.last("limit 0,"+max);
        List<QuizAnsCommentEntity> list = quizAnsCommentService.list(quizAnsCommentEntityWrapper);
        for (QuizAnsCommentEntity quizAnsCommentEntity:list){
            quizAnsCommentEntity.setStatus(1);
        }
        //在批量更新消息
        quizAnsCommentService.updateBatchById(list);
        LoadMessageR loadMessageR = new LoadMessageR();
        loadMessageR.setList(list);
        loadMessageR.setSize(list.size());
        return R.ok().put("msg",loadMessageR);
    }
}
