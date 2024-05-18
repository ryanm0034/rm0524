package com.example.heavytoolsapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "tool_type")
public class ToolType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "DAILY_CHARGE_AMOUNT", nullable = false)
    private double dailyChargeAmount;

    @Column(name = "WEEK_DAY_CHARDGE", nullable = false)
    private boolean weekDayCharge;

    @Column(name = "WEEK_END_CHARGE", nullable = false)
    private boolean weekEndCharge;

    @Column(name = "HOLIDAY_CHARGE", nullable = false)
    private boolean holidayCharge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ToolType setName(String name) {
        this.name = name;
        return this;
    }

    public double getDailyChargeAmount() {
        return dailyChargeAmount;
    }

    public ToolType setDailyChargeAmount(double dailyChargeAmount) {
        this.dailyChargeAmount = dailyChargeAmount;
        return this;
    }

    public boolean isWeekDayCharge() {
        return weekDayCharge;
    }

    public ToolType setWeekDayCharge(boolean weekDayCharge) {
        this.weekDayCharge = weekDayCharge;
        return this;
    }

    public boolean isWeekEndCharge() {
        return weekEndCharge;
    }

    public ToolType setWeekEndCharge(boolean weekEndCharge) {
        this.weekEndCharge = weekEndCharge;
        return this;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public ToolType setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
        return this;
    }

    public ToolType(String name, double dailyChargeAmount, boolean weekDayCharge, boolean weekEndCharge, boolean holidayCharge) {
        this.name = name;
        this.dailyChargeAmount = dailyChargeAmount;
        this.weekDayCharge = weekDayCharge;
        this.weekEndCharge = weekEndCharge;
        this.holidayCharge = holidayCharge;
    }
}
