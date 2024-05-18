package com.example.heavytoolsapp;

import com.example.heavytoolsapp.services.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    @Mock
    private ValidationService validationService;

    // test 1
    @Test
    public void testIsDiscountPercentValid() {
        // Arrange
        Integer validDiscountPercent = 50; // Valid discount percent
        Integer invalidDiscountPercent = 101; // Invalid discount percent

        when(validationService.isDiscountPercentValid(validDiscountPercent)).thenReturn(true);
        when(validationService.isDiscountPercentValid(invalidDiscountPercent)).thenReturn(false);

        // Act & Assert
        assertTrue(validationService.isDiscountPercentValid(validDiscountPercent));
        assertFalse(validationService.isDiscountPercentValid(invalidDiscountPercent));
    }

}
