package com.lendico.loan.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendico.loan.schedule.LoanSchedule;
import com.lendico.loan.schedule.LoanScheduleController;
import com.lendico.loan.schedule.RepaymentPlanInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationScheduleSystem implements ScheduleSystem {
    public static ScheduleSystem create(MockMvc mockMvc) {
        IntegrationScheduleSystem unitScheduleSystem = new IntegrationScheduleSystem();
        unitScheduleSystem.mockMvc = mockMvc;
        return unitScheduleSystem;

    }

    private MockMvc mockMvc;

    @Override
    public ResponseEntity<List<LoanSchedule>> schedule(RepaymentPlanInput repaymentInput) {
        ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
        MvcResult mvcResult = null;
        try {
            String content = objectMapper.writeValueAsString(repaymentInput);
            mvcResult = mockMvc.perform(post(LoanScheduleController.URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andReturn();

            HttpStatus httpStatus = HttpStatus.valueOf(mvcResult.getResponse().getStatus());
            if(httpStatus == HttpStatus.OK) {
                String resultContent = mvcResult.getResponse().getContentAsString();
                return new ResponseEntity<>(Arrays.asList(objectMapper.readValue(resultContent, LoanSchedule[].class)), httpStatus);
            }
            return new ResponseEntity<>(httpStatus);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }
}
