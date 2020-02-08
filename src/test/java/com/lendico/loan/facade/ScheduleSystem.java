package com.lendico.loan.facade;

import com.lendico.loan.schedule.LoanSchedule;
import com.lendico.loan.schedule.RepaymentPlanInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScheduleSystem {
    ResponseEntity<List<LoanSchedule>> schedule(@RequestBody RepaymentPlanInput repaymentInput);
}
