package com.lendico.loan.schedule;

import com.lendico.loan.facade.ScheduleSystem;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RequiredArgsConstructor
public class LoanScheduleTestCases {
    private final ScheduleSystem scheduleSystem;

    public void testValid(String fileName) {
        RepaymentPlanInput repaymentPlanInput = DataFactory.createRepaymentPlanInput(fileName);
        ResponseEntity<List<LoanSchedule>> result = scheduleSystem.schedule(repaymentPlanInput);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        List<LoanSchedule> loanSchedules = result.getBody();

        //validate duration
        Assertions.assertEquals(repaymentPlanInput.getDuration(), loanSchedules.size());

        List<LoanSchedule> expectedLoanSchedules = DataFactory.createLoanSchedule(fileName);
        validateLoanSchedule(loanSchedules, expectedLoanSchedules);
    }

    public void testInvalidInput() {
        for(String fileName : invalidFiles) {
            RepaymentPlanInput repaymentPlanInput = DataFactory.createRepaymentPlanInput(fileName);
            ResponseEntity<List<LoanSchedule>> result = scheduleSystem.schedule(repaymentPlanInput);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        }
    }

    private void validateLoanSchedule(List<LoanSchedule> loanSchedules, List<LoanSchedule> expectedLoanSchedules) {
        int i = 0;
        for(LoanSchedule loanSchedule : loanSchedules) {
            Assertions.assertEquals(expectedLoanSchedules.get(i), loanSchedule);
            i++;
        }
    }

    private String[] invalidFiles = {"no-amount.json", "no-date.json", "no-duration.json", "no-rate.json"};
}
