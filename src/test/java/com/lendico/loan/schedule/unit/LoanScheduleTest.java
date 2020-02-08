package com.lendico.loan.schedule.unit;

import com.lendico.loan.facade.ScheduleSystem;
import com.lendico.loan.facade.UnitScheduleSystem;
import com.lendico.loan.schedule.LoanScheduleTestCases;
import org.junit.jupiter.api.Test;

public class LoanScheduleTest {

    @Test
    public void testValidSchedule() {
        LoanScheduleTestCases testCases = create();

        testCases.testValid();
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
