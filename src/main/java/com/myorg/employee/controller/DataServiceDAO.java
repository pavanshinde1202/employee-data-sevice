package com.myorg.employee.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class DataServiceDAO {
	
	private List<EmployeeDO> employeeList;
		
	public DataServiceDAO() {
		this.employeeList = new LinkedList<EmployeeDO>();
	}

	public Optional<EmployeeDO> update(String empId, EmployeeDO employeeDO) {
		int index = this.employeeList.indexOf(employeeDO);
		if(index != -1) {
			this.employeeList.set(index, employeeDO);
		}
		return Optional.empty();
	}

	public Optional<EmployeeDO> create(String empId, EmployeeDO employeeDO) {
		//Optional<EmployeeDO> emp = this.employeeList.contains(employeeDO);
		if(this.employeeList.contains(employeeDO)) {
			return Optional.empty();
		}
		this.employeeList.add(employeeDO);
		return Optional.ofNullable(employeeDO);
	}

	public boolean delete(String empId) {
		EmployeeDO emp = new EmployeeDO();
		emp.setUserId(empId);
		return this.employeeList.remove(emp);
	}

	public List<EmployeeDO> get(String empId) {
		List<EmployeeDO> list = this.employeeList.stream().filter(x -> x.getUserId().equals(empId)).collect(Collectors.toList());
		return list;
	}

}
