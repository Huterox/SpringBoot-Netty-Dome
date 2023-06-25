package com.huterox.whitehole.whiteholemessage.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-10-27 13:56:13
 */
@Data
@TableName("msg_quiz_ans_comment")
public class QuizAnsCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属于谁的消息
	 */
	private String userid;
	/**
	 * 谁产生的消息
	 */
	private String fromid;

	/**
	 * 评论的时间
	 */
	private String creatTime;
	/**
	 * 
	 */
	private String formnickname;
	/**
	 * 1-已读取 2-未读
	 */
	private Integer status;
	/**
	 * 评论的一些内容
	 */
	private String msg;
	/**
	 * 
	 */
	private String quiztitle;

	private Long ansid;
	private Long quizid;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long msgid;
	/**
	 * 
	 */
	private String fromimg;

}
