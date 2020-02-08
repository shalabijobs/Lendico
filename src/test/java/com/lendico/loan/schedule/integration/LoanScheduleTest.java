package com.lendico.loan.schedule.integration;

import com.lendico.loan.facade.IntegrationScheduleSystem;
import com.lendico.loan.facade.ScheduleSystem;
import com.lendico.loan.schedule.LoanScheduleTestCases;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanScheduleTest {
    @Autowired
    MockMvc mockMvc;

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
        ScheduleSystem scheduleSystem = IntegrationScheduleSystem.create(mockMvc);
        return new LoanScheduleTestCases(scheduleSystem);
    }
}
