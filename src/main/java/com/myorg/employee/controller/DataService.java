package com.myorg.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Autowired
	private DataServiceDAO dataServiceDAO;
	
	public Optional<EmployeeDO> updateEmployee(String empId, EmployeeDO employeeDO) {
		return dataServiceDAO.update(empId, employeeDO);
	}

	public Optional<EmployeeDO> createEmployee(String empId, EmployeeDO employeeDO) {
		return dataServiceDAO.create(empId, employeeDO);
	}

	public boolean deleteEmployee(String empId) {
		return dataServiceDAO.delete(empId);
	}

	public List<EmployeeDO> getEmployee(String empId) {
		return dataServiceDAO.get(empId);
	}

}
