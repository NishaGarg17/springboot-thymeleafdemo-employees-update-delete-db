package com.nisha.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisha.springboot.thymeleafdemo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// that's all there is no need to writy any code....
	
	// Add a method to sort by last name
	
	public List<Employee> findAllByOrderByFirstNameAsc(); 
}
