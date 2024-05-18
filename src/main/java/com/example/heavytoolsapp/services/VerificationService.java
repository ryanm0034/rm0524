package com.example.heavytoolsapp.services;

import java.util.Date;

public interface VerificationService {

    boolean toolCodeExistenceCheck(String toolCode);

    boolean holidaysCheck(Date checkoutDate, Integer rentalDays);

    int weekendDays(Date checkoutDate, Integer rentalDays);



}
