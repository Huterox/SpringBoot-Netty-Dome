package com.huterox.whitehole.whiteholemessage.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("msg_hole_audit")
public class HoleAuditEntity implements Serializable {
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
	private String msgtitle;
	/**
	 * 
	 */
	private String linkid;


	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long msgid;
	/**
	 * 
	 */
	private Integer status;

	private Integer type;

	@TableField(value = "linkid2")
	private String linkid2;
}
