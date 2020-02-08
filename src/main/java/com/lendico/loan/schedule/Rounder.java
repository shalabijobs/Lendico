package com.lendico.loan.schedule;

import java.text.DecimalFormat;

public class Rounder {
    public static double round(double value) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        return Double.valueOf(decimalFormat.format(value));
    }
}
