package com.lendico.loan.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lendico.loan.facade.ObjectMapperFactory;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class DataFactory {
    static RepaymentPlanInput createRepaymentPlanInput(String fileName) {
        ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();
        File file = loadInputTestFile(fileName);

        try {
            return mapper.readValue(file, RepaymentPlanInput.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<LoanSchedule> createLoanSchedule(String fileName) {
        ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();
        File file = loadExpectedTestFile(fileName);

        try {
            return Arrays.asList(mapper.readValue(file, LoanSchedule[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private static File loadExpectedTestFile(String fileName) {
        return loadFile("/expected/" + fileName);
    }
    private static File loadInputTestFile(String fileName) {
        return loadFile("/input/" + fileName);
    }
    private static File loadFile(String fileName)
    {
        try {
         return ResourceUtils.getFile(
                 "classpath:data" + fileName);
        } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
        }
    }
}
