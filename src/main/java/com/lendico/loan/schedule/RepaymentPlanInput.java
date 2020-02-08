package com.lendico.loan.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RepaymentPlanInput {
    public static boolean isInvalid(RepaymentPlanInput repaymentInput) {
        return repaymentInput == null ||
                repaymentInput.getDuration() == null ||
                repaymentInput.getDuration() == 0 ||
                repaymentInput.getLoanAmount() == null ||
                repaymentInput.getLoanAmount() == 0 ||
                repaymentInput.getNominalRate() == null ||
                repaymentInput.getNominalRate() == 0 ||
                repaymentInput.getStartDate() == null;
    }
    private Double loanAmount;
    private Double nominalRate;
    private Integer duration;
    private LocalDateTime startDate;
}
