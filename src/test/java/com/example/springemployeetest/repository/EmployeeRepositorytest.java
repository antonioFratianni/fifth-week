package com.example.springemployeetest.repository;

import com.example.springemployeetest.domain.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositorytest {

    @Autowired
    EmployeeRepository employeeRepository;

    Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee(1,"Antonio", "Fratianni");
    }

    @AfterEach
    public  void tearDown() {
        employeeRepository.deleteAll();
        employee = null;
    }

    @Test
    public void givenEmployeeToAddShouldReturnAddedEmployee() {
        employeeRepository.save(employee);
        Employee fetchedProduct = employeeRepository.findById(employee.getId()).get();
        assertEquals(1,fetchedProduct.getId());
    }

    @Test
    public void givenAllEmployeesShouldReturnListOfAllEmployees () {
        Employee employee1 = new Employee(1,"Antonio", "Fratianni");
        Employee employee2 = new Employee(2,"Mario", "Rossi");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        assertEquals("Antonio", employeeList.get(0).getName());

    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheProduct (){
        Employee employee = new Employee(1,"Luigi", "Bianchi");
        employeeRepository.save(employee);
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> fetchedProduct = employeeRepository.findById(employee.getId());
        assertEquals(Optional.empty(), fetchedProduct);
    }

}
