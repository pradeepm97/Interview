package Streams.Interview.controller;

import Streams.Interview.Model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link EmployeeController}.
 */
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    /**
     * Test for {@link EmployeeController#getAllEmployeeDetails(String)}.
     * Mocks the static method {@link Employee#getEmployeeList()} to ensure isolation.
     */
    @Test
    void testGetAllEmployeeDetails_ReturnsMockedList() {
        // Arrange
        List<Employee> mockList = List.of(
                new Employee(1, "Test User", 30, "Male", "IT", 2020, 50000.0)
        );
        try (MockedStatic<Employee> mockedStatic = mockStatic(Employee.class)) {
            mockedStatic.when(Employee::getEmployeeList).thenReturn(mockList);

            EmployeeController controller = new EmployeeController();

            // Act
            List<Employee> result = controller.getAllEmployeeDetails("anyName");

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Test User", result.get(0).getName());
            mockedStatic.verify(Employee::getEmployeeList, times(1));
        }
    }
}

