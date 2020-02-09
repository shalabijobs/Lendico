package com.lendico.loan.schedule.integration;

import com.lendico.loan.facade.IntegrationScheduleSystem;
import com.lendico.loan.facade.ScheduleSystem;
import com.lendico.loan.schedule.LoanScheduleTestCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName("LoanSchedule integration tests")
public class LoanScheduleTest {
    @Autowired
    private MockMvc mockMvc;

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

    private LoanScheduleTestCases create() {
        ScheduleSystem scheduleSystem = IntegrationScheduleSystem.create(mockMvc);
        return new LoanScheduleTestCases(scheduleSystem);
    }
}
