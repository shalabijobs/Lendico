package com.lendico.loan.schedule.unit;

import com.lendico.loan.facade.ScheduleSystem;
import com.lendico.loan.facade.UnitScheduleSystem;
import com.lendico.loan.schedule.LoanScheduleTestCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LoanSchedule unit tests")
public class LoanScheduleTest {

    private LoanScheduleTestCases testCases;

    @BeforeEach
    public void setup() {
        testCases = create();
    }

    @Test
    public void testValidSchedule() {
        testCases.testValid();
    }

    @Test
    public void testInvalidInput() {
        testCases.testInvalidInput();
    }

    @Test
    @DisplayName("Edge case: single month duration")
    public void testSingleMonthDuration() {
        testCases.testValid("single-month.json");
    }

    @Test
    @DisplayName("Edge case: two months duration")
    public void testTwoMonthsDuration() {
        testCases.testValid("two-months.json");
    }

    @Test
    @DisplayName("Edge case: three months duration")
    public void testThreeMonthsDuration() {
        testCases.testValid("three-months.json");
    }


    private LoanScheduleTestCases create() {
        ScheduleSystem scheduleSystem = UnitScheduleSystem.create();
        return new LoanScheduleTestCases(scheduleSystem);
    }
}
