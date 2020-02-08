package com.lendico.loan.schedule;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LoanSchedule {
    public static LoanSchedule create(double amount, LocalDateTime date, double annuity, double interest) {
        LoanSchedule loanSchedule = new LoanSchedule();
        loanSchedule.principal = Rounder.round(annuity - interest);
        loanSchedule.date = date;
        loanSchedule.initialOutstandingPrincipal = amount;
        loanSchedule.interest = interest;
        loanSchedule.borrowerPaymentAmount = annuity;
        loanSchedule.remainingOutstandingPrincipal = Rounder.round(amount - loanSchedule.principal);

        return loanSchedule;
    }

    private double borrowerPaymentAmount;
    private LocalDateTime date;
    private double initialOutstandingPrincipal;
    private double interest;
    private double principal;
    private double remainingOutstandingPrincipal;
}


