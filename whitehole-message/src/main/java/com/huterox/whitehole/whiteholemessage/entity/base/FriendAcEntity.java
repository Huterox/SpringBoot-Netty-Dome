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
@TableName("msg_friend_ac")
public class FriendAcEntity implements Serializable {
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
	private Date createTime;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private String fromimg;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long msgid;

}
