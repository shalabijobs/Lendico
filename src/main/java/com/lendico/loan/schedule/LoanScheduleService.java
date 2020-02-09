package com.lendico.loan.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoanScheduleService {
    public List<LoanSchedule> schedule(RepaymentPlanInput repaymentInput) {
        List<LoanSchedule> loanSchedules = new ArrayList<>();
        log.debug("Calculate annuity and interest.");
        double annuity = calculateAnnuity(repaymentInput);
        double interest = calculateInterest(repaymentInput.getNominalRate(), repaymentInput.getLoanAmount());

        double total = 0;
        LocalDateTime date = repaymentInput.getStartDate();
        double amount = repaymentInput.getLoanAmount();
        for(int i=0; i<repaymentInput.getDuration() - 1; i++) {
            LoanSchedule loanSchedule = LoanSchedule.create(amount, date, annuity, interest);
            loanSchedules.add(loanSchedule);
            log.debug("calculated loan schedule " + loanSchedule);

            total += loanSchedule.getPrincipal();
            date = date.plusMonths(1);
            amount = loanSchedule.getRemainingOutstandingPrincipal();
            interest = calculateInterest(repaymentInput.getNominalRate(), amount);
        }

        LoanSchedule loanSchedule = getLastLoanSchedule(amount, date, annuity, interest);
        log.debug("calculated loan schedule for last month " + loanSchedule);
        loanSchedules.add(loanSchedule);

        return loanSchedules;
    }

    private LoanSchedule getLastLoanSchedule(double amount, LocalDateTime date, double annuity, double interest) {
        LoanSchedule loanSchedule = LoanSchedule.create(amount, date, annuity, interest);
        double remainingPrincipal = loanSchedule.getRemainingOutstandingPrincipal();
        loanSchedule.setPrincipal(Rounder.round(loanSchedule.getPrincipal() + remainingPrincipal));
        loanSchedule.setBorrowerPaymentAmount(loanSchedule.getPrincipal() + interest);
        loanSchedule.setRemainingOutstandingPrincipal(0);

        return loanSchedule;
    }

    private double calculateAnnuity(RepaymentPlanInput repaymentInput) {
        double monthlyRate = repaymentInput.getNominalRate() / 1200;
        double value = repaymentInput.getLoanAmount() * monthlyRate / (1 - calculatePower(monthlyRate, repaymentInput.getDuration()));

        return Rounder.round(value);
    }

    private double calculateInterest(double rate, double amount) {
        double interest = (rate * 30 * amount) / 36000;

        return Rounder.round(interest);
    }

    /*
    Notice this method can be cached since most likely the monthly rate will change rarely.
     */
    private double calculatePower(double monthlyRate, int months) {
        return Math.pow(1 + monthlyRate, -months);
    }
}
