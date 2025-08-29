package Streams.Interview.controller;

import Streams.Interview.Model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmployeeDetails_returnsEmployeeList() throws Exception {
        String employeeName = "anyName";
        List<Employee> expectedList = Employee.getEmployeeList();

        mockMvc.perform(get("/getEmployees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedList.size())))
                .andExpect(jsonPath("$[0].id", is(expectedList.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(expectedList.get(0).getName())))
                .andExpect(jsonPath("$[0].age", is(expectedList.get(0).getAge())))
                .andExpect(jsonPath("$[0].gender", is(expectedList.get(0).getGender())))
                .andExpect(jsonPath("$[0].department", is(expectedList.get(0).getDepartment())))
                .andExpect(jsonPath("$[0].yearOfJoining", is(expectedList.get(0).getYearOfJoining())))
                .andExpect(jsonPath("$[0].salary", is(expectedList.get(0).getSalary())));
    }
}

