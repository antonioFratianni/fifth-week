package com.example.springemployeetest.service;

import com.example.springemployeetest.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeByid(int id);

    Employee deleteEmployeeById(int id);
}
