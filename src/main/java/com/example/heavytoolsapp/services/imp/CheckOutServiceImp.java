package com.example.heavytoolsapp.services.imp;

import com.example.heavytoolsapp.dtos.CheckOutTemplate;
import com.example.heavytoolsapp.models.Tool;
import com.example.heavytoolsapp.repos.ToolCodeRepo;
import com.example.heavytoolsapp.services.CalculationService;
import com.example.heavytoolsapp.services.CheckOutService;
import com.example.heavytoolsapp.services.ValidationService;
import com.example.heavytoolsapp.services.VerificationService;
import com.example.heavytoolsapp.util.exceptions.InvalidDiscountPercentException;
import com.example.heavytoolsapp.util.exceptions.InvalidRentalDaysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CheckOutServiceImp implements CheckOutService {

    @Autowired
    ToolCodeRepo toolCodeRepo;
    @Autowired
    ValidationService validationService;

    @Autowired
    VerificationService verificationService;
    @Autowired
    CalculationService caculationService;

    @Override
    public void createCheckOut(String toolCode, Integer rentalDays, Integer discountPercent, Date checkoutDate) throws InvalidRentalDaysException, InvalidDiscountPercentException {
        // validate the rental days
        if (!validationService.isRentalDaysValid(rentalDays)) {
            throw new InvalidRentalDaysException("Rental days should be between 1 and 365");
        }
        // validate the discount percent
        if (!validationService.isDiscountPercentValid(discountPercent)) {
            throw new InvalidDiscountPercentException("Discount percent should be between 0 and 100");
        }
        // validate the checkout date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCheckoutDate = formatter.format(checkoutDate);

        if (!validationService.isCheckoutDateValid(formattedCheckoutDate)){
            throw new IllegalArgumentException("Checkout date should be in the format yyyy-MM-dd");
        }

        // verify the tool code
        if (!verificationService.toolCodeExistenceCheck(toolCode)) {
            throw new IllegalArgumentException("Tool code does not exist");
        } else {
            // get the tool
            Tool tool = toolCodeRepo.findByToolCode(toolCode);
            // initialize checkout template
            CheckOutTemplate checkoutInstance = new CheckOutTemplate(tool.getToolCode(), tool.getToolType().getName(), tool.getBrand().getName(), rentalDays, checkoutDate, tool.getToolType().getDailyChargeAmount() , discountPercent);
            // calculate the charges
            caculationService.caculate(tool, checkoutInstance);
            System.out.println(checkoutInstance.toString());
        }
    }


}
