package com.ccy.shopping.entity;

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
 * @date 2021-07-14 16:29:35
 */
@Data
@TableName("favour")
public class FavourEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer favourId;
	/**
	 * 
	 */
	private Integer usrId;
	/**
	 * 
	 */
	private Integer carId;
	/**
	 * 
	 */
	private Date time;

}
