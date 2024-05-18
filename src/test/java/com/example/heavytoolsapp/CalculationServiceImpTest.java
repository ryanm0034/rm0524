package com.example.heavytoolsapp;

import com.example.heavytoolsapp.dtos.CheckOutTemplate;
import com.example.heavytoolsapp.models.Tool;
import com.example.heavytoolsapp.models.ToolBrand;
import com.example.heavytoolsapp.models.ToolType;
import com.example.heavytoolsapp.services.ValidationService;
import com.example.heavytoolsapp.services.VerificationService;
import com.example.heavytoolsapp.services.imp.CalculationServiceImp;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CalculationServiceImpTest {

    @InjectMocks
    CalculationServiceImp calculationServiceImp;
    @Mock
    private ValidationService validationService;

    @Mock
    private VerificationService verificationService;

    private static Stream<Arguments> provideTestCases() {
        // test scenarios
        // case 2
        Tool tool2 = new Tool();
        tool2.setToolCode("LADW");
        tool2.setToolType(new ToolType("Ladder", 1.99, true, true, false)); // Assuming ToolType is a class with a constructor that accepts name, holidayCharge, weekEndCharge, and dailyChargeAmount
        tool2.setBrand(new ToolBrand("Werner")); // Initialize this with appropriate values

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2020);
        calendar2.set(Calendar.MONTH, Calendar.JULY);
        calendar2.set(Calendar.DAY_OF_MONTH, 2);
        Date checkOutDate2 = calendar2.getTime();

        //case 3
        Tool tool3 = new Tool();
        tool3.setToolCode("CHNS");
        tool3.setToolType(new ToolType("Chainsaw", 1.49, true, false, true)); // Assuming ToolType is a class with a constructor that accepts name, holidayCharge, weekEndCharge, and dailyChargeAmount
        tool3.setBrand(new ToolBrand("Stihl")); // Initialize this with appropriate values

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.YEAR, 2015);
        calendar3.set(Calendar.MONTH, Calendar.JULY);
        calendar3.set(Calendar.DAY_OF_MONTH, 2);
        Date checkOutDate3 = calendar3.getTime();
        //case 4
        Tool tool4 = new Tool();
        tool4.setToolCode("JAKD");
        tool4.setToolType(new ToolType("Jackhammer", 2.99, true, false, false)); // Assuming ToolType is a class with a constructor that accepts name, holidayCharge, weekEndCharge, and dailyChargeAmount
        tool4.setBrand(new ToolBrand("DeWalt")); // Initialize this with appropriate values

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(Calendar.YEAR, 2015);
        calendar4.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar4.set(Calendar.DAY_OF_MONTH, 3);
        Date checkOutDate4 = calendar4.getTime();
        //case 5
        Tool tool5 = new Tool();
        tool5.setToolCode("JAKR");
        tool5.setToolType(new ToolType("Jackhammer", 2.99, true, false, false)); // Assuming ToolType is a class with a constructor that accepts name, holidayCharge, weekEndCharge, and dailyChargeAmount
        tool5.setBrand(new ToolBrand("Ridgid")); // Initialize this with appropriate values

        // checkoutDate case 3 =  case 5

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(Calendar.YEAR, 2020);
        calendar6.set(Calendar.MONTH, Calendar.JULY);
        calendar6.set(Calendar.DAY_OF_MONTH, 2);
        Date checkOutDate6 = calendar6.getTime();
        //case 6
        Tool tool6 = new Tool();
        tool6.setToolCode("JAKR");
        tool6.setToolType(new ToolType("Jackhammer", 2.99, true, false, false)); // Assuming ToolType is a class with a constructor that accepts name, holidayCharge, weekEndCharge, and dailyChargeAmount
        tool6.setBrand(new ToolBrand("Ridgid")); // Initialize this with appropriate values


        return Stream.of(
                Arguments.of(tool2, checkOutDate2, 10, 3, 1, true, new BigDecimal("3.98"), new BigDecimal("0.40"), new BigDecimal("3.58")),
                Arguments.of(tool3, checkOutDate3, 25, 5, 2, true, new BigDecimal("4.47"), new BigDecimal("1.12"), new BigDecimal("3.35")),
                Arguments.of(tool4, checkOutDate4, 0, 6, 2, true, new BigDecimal("8.97"), new BigDecimal("0.00"), new BigDecimal("8.97")),
                Arguments.of(tool5, checkOutDate3, 0, 9, 2, true, new BigDecimal("17.94"), new BigDecimal("0.00"), new BigDecimal("17.94")),
                Arguments.of(tool5, checkOutDate3, 50, 4, 2, true, new BigDecimal("2.99"), new BigDecimal("1.50"), new BigDecimal("1.50"))

                // Add more test cases here
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testCalculate(Tool tool, Date checkoutDate, Integer discountPercent, int rentalDays, int weekendDays, boolean isHoliday, BigDecimal expectedPreDiscountCharge, BigDecimal expectedDiscountAmount, BigDecimal expectedFinalCharge) {
        // Arrange

        CheckOutTemplate checkoutInstance
                = new CheckOutTemplate(tool.getToolCode(), tool.getToolType().getName(), tool.getBrand().getName(), rentalDays, checkoutDate, tool.getToolType().getDailyChargeAmount(), discountPercent); // Initialize this with appropriate values

        when(verificationService.holidaysCheck(checkoutDate, rentalDays)).thenReturn(isHoliday);
        when(verificationService.weekendDays(checkoutDate, rentalDays)).thenReturn(weekendDays);

        // Act
        calculationServiceImp.caculate(tool, checkoutInstance);

        // Assert
        assertEquals(expectedPreDiscountCharge, checkoutInstance.getPreDiscountCharge());


        DecimalFormat df = new DecimalFormat("0.00");
        String formattedValue = df.format(expectedDiscountAmount);
        assertEquals(new BigDecimal(formattedValue), checkoutInstance.getDiscountAmount());

        assertEquals(expectedFinalCharge, checkoutInstance.getFinalCharge());
    }
}
