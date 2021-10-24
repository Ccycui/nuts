package com.ccy.display.display.entity;

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
 * @date 2021-07-14 11:10:02
 */
@Data
@TableName("car_info")
public class InfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer carId;
	/**
	 * 
	 */
	private String carBrand;
	/**
	 * 
	 */
	private String carModel;
	/**
	 * 
	 */
	private String carType;
	/**
	 * 
	 */
	private Integer carYear;
	/**
	 * 
	 */
	private Integer carMile;
	/**
	 * 
	 */
	private String carColor;

}
