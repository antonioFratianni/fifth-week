package com.example.springemployeetest.controller;

import com.example.springemployeetest.domain.Employee;
import com.example.springemployeetest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Employee employee;
    private List<Employee> employeeList;

    @InjectMocks
    private EmployeeController employeeController;


    @BeforeEach
    public void setup() {
        employee = new Employee(1, "Antonio", "Fratianni");
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @AfterEach
    public void tearDown() {
        employee = null;
    }

    @Test
    public void GetMappingOfAllProduct() throws Exception {
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee))).andDo(MockMvcResultHandlers.print());
        verify(employeeService).getAllEmployee();
        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    public void PostMappingOfProduct() throws Exception {
        when(employeeService.addEmployee(any())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee))).andDo(MockMvcResultHandlers.print());
        verify(employeeService).addEmployee(any());
        verify(employeeService, times(1)).addEmployee(any());
    }

    @Test
    public void GetMappingOfProductShouldReturnRespectiveProduct() throws Exception {
        when(employeeService.getEmployeeByid(employee.getId())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void DeleteMappingUrlAndIdThenShouldReturnDeletedProduct() throws Exception {
        when(employeeService.deleteEmployeeById(employee.getId())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
