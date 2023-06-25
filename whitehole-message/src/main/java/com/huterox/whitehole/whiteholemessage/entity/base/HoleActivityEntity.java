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
@TableName("msg_hole_activity")
public class HoleActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String userid;
	/**
	 * 
	 */
	private String msg;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long msgid;
	/**
	 * 
	 */
	private String msgtitle;
	/**
	 * 
	 */
	private Long linkid;
	/**
	 * 
	 */
	private Date createTime;

}
