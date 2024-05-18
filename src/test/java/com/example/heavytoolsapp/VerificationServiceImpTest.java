package com.example.heavytoolsapp;

import com.example.heavytoolsapp.services.imp.VerificationServiceImp;
import com.example.heavytoolsapp.repos.ToolCodeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VerificationServiceImpTest {

    @Mock
    private ToolCodeRepo toolCodeRepo;

    @InjectMocks
    private VerificationServiceImp verificationServiceImp;

    @Test
    public void testToolCodeExistenceCheck() {
        // Arrange
        String toolCode = "123";
        when(toolCodeRepo.existsByToolCode(toolCode)).thenReturn(true);

        // Act
        boolean result = verificationServiceImp.toolCodeExistenceCheck(toolCode);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testHolidaysCheck() {
        // Arrange
        Date checkoutDate = new Date(); // Initialize this with a date that is a holiday
        Integer rentalDays = 5;

        // Act
        boolean result = verificationServiceImp.holidaysCheck(checkoutDate, rentalDays);

        // Assert
        assertEquals(true, result); // This should be true if the checkoutDate is a holiday
    }

    @Test
    public void testWeekendDays() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, Calendar.JULY); // In Calendar class, January starts at 0
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        Date checkOutDate = calendar.getTime();
        Integer rentalDays = 3;

        // Act
        int result = verificationServiceImp.weekendDays(checkOutDate, rentalDays);

        // Assert
        assertEquals(1, result); // This should be the number of weekend days in the rental period
    }
}
