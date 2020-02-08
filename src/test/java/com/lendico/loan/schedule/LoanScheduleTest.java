package com.lendico.loan.schedule;

import com.lendico.loan.facade.ScheduleSystem;
import com.lendico.loan.facade.UnitScheduleSystem;
import org.junit.jupiter.api.Test;

public class LoanScheduleTest {

    @Test
    public void testValidSchedule() {
        LoanScheduleTestCases testCases = create();

        testCases.testValid("5000-24-5.json");
    }

    @Test
    public void testInvalidInput() {
        LoanScheduleTestCases testCases = create();

        testCases.testInvalidInput();
    }

    private LoanScheduleTestCases create() {
        ScheduleSystem scheduleSystem = UnitScheduleSystem.create();
        return new LoanScheduleTestCases(scheduleSystem);
    }
}
