package com.ccy.display.display.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author cuichengyuan
 * @email 318169204@qq.com
 * @date 2021-07-14 10:24:08
 */
@Data
@TableName("car")
public class CarEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer carId;
	/**
	 * 
	 */
	private String carName;
	/**
	 * 1-未售出 0-已售出
	 */
	private Integer carState;
	/**
	 * 
	 */
	private BigDecimal carPrice;
	/**
	 * 
	 */
	private String carPicture;

}
