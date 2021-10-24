package com.ccy.display;


import com.ccy.display.display.entity.CarEntity;
import com.ccy.display.display.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisplayApplicationTests {
	@Autowired
	CarService carService;



	@Test
	public void contextLoads() {
		CarEntity carEntity = new CarEntity();
		carEntity.setCarName("法拉利488");
		carEntity.setCarPrice(new BigDecimal(500.48));
		carService.save(carEntity);
		System.out.println("保存成功");
	}

}
