package com.example.springemployeetest.service;

import com.example.springemployeetest.domain.Employee;
import com.example.springemployeetest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByid(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee deleteEmployeeById(int id) {
        Employee employee = null;
        Optional<?> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            employee = employeeRepository.findById(id).get();
            employeeRepository.deleteById(id);
        }
        return employee;
    }
}
