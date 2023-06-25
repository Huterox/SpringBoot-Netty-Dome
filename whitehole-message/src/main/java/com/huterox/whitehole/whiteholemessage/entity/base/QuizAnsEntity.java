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
@TableName("msg_quiz_ans")
public class QuizAnsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String userid;
	/**
	 * 
	 */
	private String fromid;
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
	private Long ansid;
	/**
	 * 
	 */
	private Long quizid;
	/**
	 * 
	 */
	private String creatTime;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long msgid;
	/**
	 * 
	 */
	private String fromimg;
	/**
	 * 
	 */
	private Integer status;

}
