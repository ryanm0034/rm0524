package com.example.heavytoolsapp.services.imp;

import com.example.heavytoolsapp.dtos.CheckOutTemplate;
import com.example.heavytoolsapp.models.Tool;
import com.example.heavytoolsapp.services.CalculationService;
import com.example.heavytoolsapp.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CalculationServiceImp implements CalculationService {

    @Autowired
    VerificationService verificationService;



    @Override
    public void caculate(Tool tool, CheckOutTemplate checkOutInstant) {
        int chargeDays = checkOutInstant.getRentalDays();

        Date dueDate = Date.from(checkOutInstant.getCheckoutDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(chargeDays).atStartOfDay(ZoneId.systemDefault()).toInstant());
        checkOutInstant.setDueDate(dueDate);

        boolean holidayCharge = tool.getToolType().isHolidayCharge();
        boolean weekEndCharge = tool.getToolType().isWeekEndCharge();

        if (!holidayCharge) {
            boolean isHoliday = verificationService.holidaysCheck(checkOutInstant.getCheckoutDate(), checkOutInstant.getRentalDays());
            chargeDays = isHoliday == false ? checkOutInstant.getRentalDays() : chargeDays - 1;
        }
        if (!weekEndCharge) {
            int weekendDaysIncluded = verificationService.weekendDays(checkOutInstant.getCheckoutDate(), checkOutInstant.getRentalDays());
            chargeDays = chargeDays - weekendDaysIncluded;
        }


        double preDiscountCharge = checkOutInstant.getDailyRentalCharge() * chargeDays;
        checkOutInstant.setPreDiscountCharge(new BigDecimal(preDiscountCharge).setScale(2, RoundingMode.HALF_UP));

        double disCountAmount = preDiscountCharge * checkOutInstant.getDiscountPercentage() / 100;
        checkOutInstant.setDiscountAmount(new BigDecimal(disCountAmount).setScale(2, RoundingMode.HALF_UP));

        double finalCharge = preDiscountCharge - disCountAmount;
        checkOutInstant.setFinalCharge(new BigDecimal(finalCharge).setScale(2, RoundingMode.HALF_UP));

    }
}
