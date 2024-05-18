package com.example.heavytoolsapp;

import com.example.heavytoolsapp.models.Tool;
import com.example.heavytoolsapp.models.ToolBrand;
import com.example.heavytoolsapp.models.ToolType;
import com.example.heavytoolsapp.services.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class HeavyToolsAppApplication implements CommandLineRunner {

	@Autowired
	private CheckOutService checkOutService ;

	public static void main(String[] args) {
		SpringApplication.run(HeavyToolsAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tool tool2 = new Tool();
		tool2.setToolCode("LADW");
		tool2.setToolType(new ToolType("Ladder", 1.99, true, true, false)); // Assuming ToolType is a class with a constructor that accepts name, holidayCharge, weekEndCharge, and dailyChargeAmount
		tool2.setBrand(new ToolBrand("Werner")); // Initialize this with appropriate values

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, 2020);
		calendar2.set(Calendar.MONTH, Calendar.JULY);
		calendar2.set(Calendar.DAY_OF_MONTH, 2);
		Date checkOutDate2 = calendar2.getTime();

		checkOutService.createCheckOut(tool2.getToolCode(), 5, 10, checkOutDate2);

	}
}
