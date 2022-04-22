package com.example.springemployeetest.controller;

import com.example.springemployeetest.domain.Employee;
import com.example.springemployeetest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee saveProduct = employeeService.addEmployee(employee);
        return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
    }

    @GetMapping("employees")
    public ResponseEntity<List<Employee>> getAllEmployee() {

        return new ResponseEntity<List<Employee>>(
                (List<Employee>) employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getProductById(@PathVariable("id") int id) {
        return new ResponseEntity<>(employeeService.getEmployeeByid(id), HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Employee> deleteProduct(@PathVariable("id") int id) {
        ResponseEntity<Employee> responseEntity;
        Employee deletedProduct = employeeService.deleteEmployeeById(id);
        responseEntity = new ResponseEntity<Employee>(deletedProduct, HttpStatus.OK);

        return responseEntity;
    }
}

