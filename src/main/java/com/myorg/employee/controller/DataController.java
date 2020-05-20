package com.myorg.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/employees/{employeeId}/employee-details")
public class DataController {

	@Autowired
	private DataService dataService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<EmployeeDO> updateEmployeeDetails(@PathVariable("employeeId") String empId, @RequestBody EmployeeDO employeeDO){
		employeeDO.setUserId(empId);
		Optional<EmployeeDO> optional = dataService.updateEmployee(empId, employeeDO);
		if(optional.isPresent()) {
			return new ResponseEntity<EmployeeDO>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<EmployeeDO>(new EmployeeDO(), HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<EmployeeDO> registerEmployeeDetails(@PathVariable("employeeId") String empId, @RequestBody EmployeeDO employeeDO){
		employeeDO.setUserId(empId);
		Optional<EmployeeDO> optional = dataService.createEmployee(empId, employeeDO);
		if(optional.isPresent()) {
			return new ResponseEntity<EmployeeDO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<EmployeeDO>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<EmployeeDO> getEmployeeDetails(@PathVariable("employeeId") String empId){
		List<EmployeeDO> list = dataService.getEmployee(empId);
		if(list.isEmpty()) return new ResponseEntity<EmployeeDO>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<EmployeeDO>(list.get(0), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<EmployeeDO> deleteEmployeeDetails(@PathVariable("employeeId") String empId){
		if(dataService.deleteEmployee(empId)) return new ResponseEntity<EmployeeDO>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<EmployeeDO>(HttpStatus.NO_CONTENT);
	}

}
