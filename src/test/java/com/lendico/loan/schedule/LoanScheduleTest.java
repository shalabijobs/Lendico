package com.lendico.loan.schedule;

import com.lendico.loan.facade.ScheduleSystem;
import com.lendico.loan.facade.UnitScheduleSystem;
import org.junit.jupiter.api.Test;

public class LoanScheduleTest {

    @Test
    public void testValidSchedule() {
        ScheduleSystem scheduleSystem = UnitScheduleSystem.create();
        LoanScheduleTestCases testCases = new LoanScheduleTestCases(scheduleSystem);

        testCases.testValid("5000-24-5.json");
    }

    @Test
    public void testInvalidInput() {
        ScheduleSystem scheduleSystem = UnitScheduleSystem.create();
        LoanScheduleTestCases testCases = new LoanScheduleTestCases(scheduleSystem);

        testCases.testInvalidInput();
    }
}
