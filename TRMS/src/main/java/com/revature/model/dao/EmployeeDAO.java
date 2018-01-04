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
			
			String p1 = "( FIRSTNAME, LASTNAME, PASSWD, USERNAME, EMAIL, AMOUNT_USED, EMPLOYEETYPEID)";
			String p2 = "(\'" + e.getFirstName() + "' , '" + e.getLastName() + "', '" + e.getPassword() 
				+ "', '" + e.getUsername() + "', '" + e.getEmail() + "', " + 1000 + ", " + e.getEmployeetypeId() +")";
			String sql = "INSERT INTO EMPLOYEE " + p1 + " VALUES " + p2;
					
			System.out.println(sql);
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
	
	
	public int checkEmployee(String username, String password) {
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			String sql = "SELECT EMPLOYEEID, USERNAME, PASSWD FROM EMPLOYEE WHERE USERNAME = \'" 
					+ username + "\' AND PASSWD=\'" + password + "\'";
					
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int rid = 0;
			
			if (rs.next()) {    
			    rid = rs.getInt("EMPLOYEEID");
				rs.close();
				ps.close();
			    return rid;
			} 
			else{
				System.out.println("User does not exist. Please check your username or password"); 
				rs.close();
				ps.close();
				return 0;
				
			}
			//System.out.println("Closing");
			
			
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			
		}
		return 0;
	}
	
	
	public int getEmployeeType(int eid) {
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			String sql = "SELECT EMPLOYEETYPEID FROM EMPLOYEE WHERE EMPLOYEEID = " + eid; 
					
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int typeid = 0;
			if (rs.next()) {    
			    typeid = rs.getInt("EMPLOYEETYPEID");
				rs.close();
				ps.close();
			    return typeid;
			} 
			return 0;
		}catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			
		}
		return 0;
	}
}
