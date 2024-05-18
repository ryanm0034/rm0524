package com.example.heavytoolsapp.services;

import org.springframework.stereotype.Service;


public interface ValidationService {

    boolean isNumber(String toolCode);
    boolean isDateValid(String date);
    boolean isRentalDaysValid(Integer rentalDays);
    boolean isDiscountPercentValid(Integer discountPercent);
    boolean isCheckoutDateValid(String checkoutDate);
}
