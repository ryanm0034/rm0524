package com.example.heavytoolsapp.services;

import com.example.heavytoolsapp.dtos.CheckOutTemplate;
import com.example.heavytoolsapp.models.Tool;

public interface CalculationService {

    public void caculate(Tool tool, CheckOutTemplate checkOutInstant);

}
