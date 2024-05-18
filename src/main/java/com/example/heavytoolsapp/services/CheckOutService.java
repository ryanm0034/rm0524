package com.example.heavytoolsapp.services;

import com.example.heavytoolsapp.util.exceptions.InvalidDiscountPercentException;
import com.example.heavytoolsapp.util.exceptions.InvalidRentalDaysException;
import lombok.Data;

import java.util.Date;

public interface CheckOutService {

    void createCheckOut(String toolCode , Integer rentalDays, Integer discountPercent, Date checkoutDate) throws InvalidRentalDaysException, InvalidDiscountPercentException;


}
