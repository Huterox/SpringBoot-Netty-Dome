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
@TableName("msg_quiz_thumb")
public class QuizThumbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String userid;
	/**
	 * 1-提问点赞2-回答点赞
	 */
	private Integer type;
	/**
	 * 
	 */
	private Long typeid;
	/**
	 * 
	 */
	private String formid;
	/**
	 * 
	 */
	private String fromnickname;
	/**
	 * 
	 */
	private String quiztitle;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long msgid;
	/**
	 * 
	 */
	private Date creatTime;
	/**
	 * 
	 */
	private String fromimg;
	/**
	 * 
	 */
	private Integer status;

}
