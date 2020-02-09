package com.lendico.loan.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoanScheduleController {
    public static final String URL = "/generate-plan";

    private final LoanScheduleService loanScheduleService;

    @PostMapping(LoanScheduleController.URL)
    public ResponseEntity<List<LoanSchedule>> schedule(@RequestBody RepaymentPlanInput repaymentInput) {
        if(isInvalid(repaymentInput)) {
            log.debug("Invalid request: RepaymentPlanInput has invalid or missing value.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(loanScheduleService.schedule(repaymentInput));
    }

    private boolean isInvalid(RepaymentPlanInput repaymentInput) {
        return repaymentInput == null ||
                repaymentInput.getDuration() == null ||
                repaymentInput.getDuration() == 0 ||
                repaymentInput.getLoanAmount() == null ||
                repaymentInput.getLoanAmount() == 0 ||
                repaymentInput.getNominalRate() == null ||
                repaymentInput.getNominalRate() == 0 ||
                repaymentInput.getStartDate() == null;
    }
}
