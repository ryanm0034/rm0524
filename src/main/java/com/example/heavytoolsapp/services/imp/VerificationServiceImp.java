package com.example.heavytoolsapp.services.imp;

import com.example.heavytoolsapp.repos.ToolCodeRepo;
import com.example.heavytoolsapp.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class VerificationServiceImp implements VerificationService {

    @Autowired
    ToolCodeRepo toolCodeRepo;

    @Override
    public boolean toolCodeExistenceCheck(String toolCode) {
        return toolCodeRepo.existsByToolCode(toolCode);
    }

    @Override
    public boolean holidaysCheck(Date checkoutDate, Integer rentalDays) {
        LocalDate localDate = checkoutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (int i = 0; i < rentalDays; i++) {
            LocalDate currentDate = localDate.plusDays(i);

            // Check if the date is Independence Day
            if (currentDate.getMonthValue() == 7 && currentDate.getDayOfMonth() == 4) {
                return true;
            }

            // Check if the date is the observed holiday for Independence Day
            if (currentDate.getDayOfWeek().getValue() == 5 && currentDate.plusDays(1).getMonthValue() == 7 && currentDate.plusDays(1).getDayOfMonth() == 4) {
                return true;
            }
            if (currentDate.getDayOfWeek().getValue() == 1 && currentDate.minusDays(1).getMonthValue() == 7 && currentDate.minusDays(1).getDayOfMonth() == 4) {
                return true;
            }

            // Check if the date is Labor Day (first Monday in September)
            if (currentDate.getMonthValue() == 9 && currentDate.getDayOfWeek().getValue() == 1 && currentDate.getDayOfMonth() <= 7) {
                return true;
            }
        }
        // if not returned on any of the holidays
        return false;
    }

    @Override
    public int weekendDays(Date checkoutDate, Integer rentalDays) {
        LocalDate localDate = checkoutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int weekendDays = 0;

        for (int i = 0; i < rentalDays; i++) {
            LocalDate currentDate = localDate.plusDays(i);
            int dayOfWeek = currentDate.getDayOfWeek().getValue();

            if (dayOfWeek == 6 || dayOfWeek == 7) { // 6 = Saturday, 7 = Sunday
                weekendDays++;
            }
        }

        return weekendDays;
    }
}
