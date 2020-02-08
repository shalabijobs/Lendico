package com.lendico.loan.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanScheduleController {
    public static final String URL = "generate-plan";

    private final LoanScheduleService loanScheduleService;

    @PostMapping(LoanScheduleController.URL)
    public ResponseEntity<List<LoanSchedule>> schedule(@RequestBody RepaymentPlanInput repaymentInput) {
        if(isInvalid(repaymentInput)) {
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

    private ZoneId getZone() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2016-04-21T00:00:00+0530", formatter);
        return  zonedDateTime.getZone();
    }
}
