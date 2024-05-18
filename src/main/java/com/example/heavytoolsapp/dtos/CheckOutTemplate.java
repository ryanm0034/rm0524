package com.example.heavytoolsapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CheckOutTemplate {
    private String toolCode;
    private String toolType;
    private String brand;
    private int rentalDays;
    private Date checkoutDate;
    private Date dueDate;
    private double DailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercentage;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public CheckOutTemplate(String toolCode, String toolType, String brand, int rentalDays, Date checkoutDate, double dailyRentalCharge , int discountPercentage) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.DailyRentalCharge = dailyRentalCharge;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "CheckOutTemplate{" +
                "toolCode='" + toolCode + '\'' +
                ", toolType='" + toolType + '\'' +
                ", brand='" + brand + '\'' +
                ", rentalDays=" + rentalDays +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", DailyRentalCharge=" + DailyRentalCharge +
                ", chargeDays=" + chargeDays +
                ", preDiscountCharge=" + preDiscountCharge +
                ", discountPercentage=" + discountPercentage +
                ", discountAmount=" + discountAmount +
                ", finalCharge=" + finalCharge +
                '}';
    }
}
