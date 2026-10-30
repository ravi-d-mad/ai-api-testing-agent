package com.ravi.apitesting.service;

import com.ravi.apitesting.entity.Employee;
import com.ravi.apitesting.dto.EmployeeRequest;
import com.ravi.apitesting.repo.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee create(EmployeeRequest employeeRequest){
        Employee employee = new Employee(
                employeeRequest.name(),
                employeeRequest.email(),
                employeeRequest.department()
        );
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(UUID id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for"+id));
    }

    public Employee update(UUID id, EmployeeRequest employeeRequest){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for"+id));
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setDepartment(employeeRequest.department());

        return employeeRepository.save(employee);
    }

    public void delete(UUID id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for"+id));
        employeeRepository.delete(employee);
    }
}
