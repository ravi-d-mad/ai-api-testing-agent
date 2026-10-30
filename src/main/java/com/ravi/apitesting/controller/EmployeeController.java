package com.ravi.apitesting.controller;

import com.ravi.apitesting.entity.Employee;
import com.ravi.apitesting.dto.EmployeeRequest;
import com.ravi.apitesting.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.create(employeeRequest);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable UUID id){
        return employeeService.findById(id);
    }

    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable UUID id,
                           @Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.update(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        employeeService.delete(id);
    }
}
