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
 * @date 2021-07-14 21:50:09
 */
@Data
@TableName("usr")
public class UsrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer usrId;
	/**
	 * 
	 */
	private String acc;
	/**
	 * 
	 */
	private String pwd;

}
