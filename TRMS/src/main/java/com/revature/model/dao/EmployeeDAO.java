package com.revature.model.dao;

import com.revature.model.beans.Employee;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.util.ConnectionUtil;


public class EmployeeDAO {
	//add employee to database/account creation
	
	public boolean addEmployee(Employee e) {
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			String p1 = "(firstName, lastName, PASSWORD, USERNAME, EMAIL)";
			String p2 = "(" + e.getFirstName() + ", " + e.getLastName() + ", " + e.getPassword() + ", " + e.getUsername() + ", " + e.getEmail() + ")";
			String sql = "INSERT INTO EMPLOYEE " + p1 + " VALUES " + p2;
					
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			//System.out.println("Closing");
			rs.close();
			ps.close();
			return true;
			
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			
		}
		return false;
	}
}
