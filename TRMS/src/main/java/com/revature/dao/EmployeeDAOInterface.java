package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDAOInterface {
	public List<Employee> getAllEmployees();
	public int login(String username, String password);
	public void createAccount(Employee e);
	public int getTableSize();
	public void submitForm(int rid, double amount);
	public boolean checkManager(int id);
	public double getReimbursement(int id);
}
