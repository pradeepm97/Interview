package Streams.Interview.controller;

import Streams.Interview.Model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling employee-related endpoints.
 * <p>
 * Provides an endpoint to retrieve a list of all employees.
 */
@RestController
public class EmployeeController {

    /**
     * Retrieves the list of all employees.
     * <p>
     * This endpoint returns a list of {@link Employee} objects. The request body parameter
     * {@code employeeName} is currently unused and does not affect the result.
     *
     * @param employeeName the name of the employee (currently unused)
     * @return a list of all employees
     */
    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployeeDetails(@RequestBody String employeeName) {

        return Employee.getEmployeeList();

    }

}
