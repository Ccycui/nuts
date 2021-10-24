package com.ccy.login.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-16 23:21:06
 */
@Data
@TableName("addr")
public class AddrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer addrId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String addr;

}
