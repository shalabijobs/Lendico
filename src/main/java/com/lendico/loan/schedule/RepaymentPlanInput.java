package com.lendico.loan.schedule;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class RepaymentPlanInput {
    private Double loanAmount;
    private Double nominalRate;
    private Integer duration;
    private LocalDateTime startDate;
}
