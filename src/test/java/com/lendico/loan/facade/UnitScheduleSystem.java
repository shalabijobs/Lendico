package com.lendico.loan.facade;

import com.lendico.loan.schedule.LoanSchedule;
import com.lendico.loan.schedule.LoanScheduleController;
import com.lendico.loan.schedule.LoanScheduleService;
import com.lendico.loan.schedule.RepaymentPlanInput;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UnitScheduleSystem implements ScheduleSystem {
    public static ScheduleSystem create() {
        LoanScheduleService loanScheduleService = new LoanScheduleService();
        LoanScheduleController loanScheduleController = new LoanScheduleController(loanScheduleService);

        UnitScheduleSystem unitScheduleSystem = new UnitScheduleSystem();
        unitScheduleSystem.loanScheduleController = loanScheduleController;

        return unitScheduleSystem;

    }
    private LoanScheduleController loanScheduleController;

    @Override
    public ResponseEntity<List<LoanSchedule>> schedule(RepaymentPlanInput repaymentInput) {
        return loanScheduleController.schedule(repaymentInput);
    }
}
