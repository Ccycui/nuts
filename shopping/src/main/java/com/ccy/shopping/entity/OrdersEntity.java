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
@TableName("orders")
public class OrdersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer orderId;
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
	/**
	 * 
	 */
	private Integer state;
	/**
	 * 
	 */
	private Integer express;
	/**
	 * 
	 */
	private String address;

}
