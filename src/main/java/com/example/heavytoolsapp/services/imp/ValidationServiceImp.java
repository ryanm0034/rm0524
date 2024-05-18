package com.example.heavytoolsapp.services.imp;

import com.example.heavytoolsapp.services.ValidationService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ValidationServiceImp  implements ValidationService {

    @Override
    public boolean isNumber(String toolCode) {
        try {
            Integer.parseInt(toolCode);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean isDateValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public boolean isRentalDaysValid(Integer rentalDays) {
        if (rentalDays < 1 || rentalDays > 365) return false;
        else return true;
    }

    @Override
    public boolean isDiscountPercentValid(Integer discountPercent) {
        // discount percent should be between 0 and 100
        if (discountPercent >= 0 && discountPercent <= 100) return true;
        else return false;
    }

    @Override
    public boolean isCheckoutDateValid(String checkoutDate) {
        if (checkoutDate == null || checkoutDate.isEmpty()  || !isDateValid(checkoutDate)) return false;
        else return true;
    }
}
