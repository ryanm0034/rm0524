package com.example.heavytoolsapp;

import com.example.heavytoolsapp.dtos.CheckOutTemplate;
import com.example.heavytoolsapp.models.Tool;
import com.example.heavytoolsapp.repos.ToolCodeRepo;
import com.example.heavytoolsapp.services.CalculationService;
import com.example.heavytoolsapp.services.ValidationService;
import com.example.heavytoolsapp.services.VerificationService;
import com.example.heavytoolsapp.services.imp.CheckOutServiceImp;
import com.example.heavytoolsapp.util.exceptions.InvalidDiscountPercentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckOutServiceImpTest {

    @InjectMocks
    private CheckOutServiceImp checkOutServiceImp;

    @Mock
    private ToolCodeRepo toolCodeRepo;

    @Mock
    private ValidationService validationService;

    @Mock
    private VerificationService verificationService;

    @Mock
    private CalculationService calculationService;

    // test 1
    @Test
    public void testCreateCheckOut_InvalidDiscountPercentException() throws Exception {
        // Arrange
        String toolCode = "JAKR";
        Integer rentalDays = 5;
        Integer discountPercent = 101; // Invalid discount percent
        Date checkoutDate = new Date();

        when(validationService.isNumber(toolCode)).thenReturn(true);
        when(validationService.isRentalDaysValid(rentalDays)).thenReturn(true);
        when(validationService.isDiscountPercentValid(discountPercent)).thenReturn(false); // Invalid discount percent

        // Act & Assert
        assertThrows(InvalidDiscountPercentException.class, () -> {
            checkOutServiceImp.createCheckOut(toolCode, rentalDays, discountPercent, checkoutDate);
        });
        // verify isDiscountPercentValid() is called once
        verify(validationService, times(1)).isDiscountPercentValid(discountPercent);

    }


}
