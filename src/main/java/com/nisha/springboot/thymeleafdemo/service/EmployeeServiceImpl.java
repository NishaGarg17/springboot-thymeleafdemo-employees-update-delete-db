package com.nisha.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisha.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.nisha.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		
		Optional<Employee> theEmployee = employeeRepository.findById(theId);
		Employee employee = null;
		if(theEmployee.isPresent()) {
			employee = theEmployee.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Not Able to find an Employee of Id:- " + theId);
		}
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

}
