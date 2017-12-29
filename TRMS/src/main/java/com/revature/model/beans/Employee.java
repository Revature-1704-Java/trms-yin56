package com.revature.model.beans;

public class Employee {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private int id;
	private int supervisor;
	private int deptHead;
	private double amount_used;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}
	public int getDeptHead() {
		return deptHead;
	}
	public void setDeptHead(int deptHead) {
		this.deptHead = deptHead;
	}
	public double getAmount_used() {
		return amount_used;
	}
	public void setAmount_used(double amount_used) {
		this.amount_used = amount_used;
	}
	
	
}
