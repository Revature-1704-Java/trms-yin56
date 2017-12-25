package com.revature.dao;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import com.revature.beans.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAO implements EmployeeDAOInterface {

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;	
		Employee e = null;
		List<Employee> employees = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			//System.out.println("Connection Successful");
			String sql = "SELECT * FROM EMPLOYEEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst() ) {    
			    System.out.println("No data"); 
			}
			
			while (rs.next()) {
				//System.out.println("hi");
				String fname = rs.getString("FIRSTNAME");
				//System.out.println("firstname is" + fname);
				String lname = rs.getString("LASTNAME");
				String password = rs.getString("PASSWD");
				String username = rs.getString("USERNAME");
				int employeeid = rs.getInt("EMPLOYEEID");
				e = new Employee(fname, lname, employeeid, username , password);
				//b = new Bear(id, name, age, weight);
				employees.add(e);
				
			}
			//System.out.println("Closing");
			rs.close();
			ps.close();
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		
		return employees;
		
	}

	public int login(String username, String password) {
		PreparedStatement ps = null;	
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT EMPLOYEEID, USERNAME, PASSWD FROM EMPLOYEEE WHERE USERNAME = \'" 
					+ username  + "\' AND PASSWD = \'" +   password + "\'";
			//System.out.println(sql);
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
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		return 0;
	}

	public void createAccount(Employee e) {
		PreparedStatement ps = null;	
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Connection Successful");
			
			int nextidvalue = getTableSize() + 1;
			
			String sql2 = "INSERT INTO REIMBURSEMENT VALUES(" + nextidvalue +  ", " + 0.0 + ")";
			
			//System.out.println(sql2);
			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			
			String sql = "INSERT INTO EMPLOYEEE VALUES(" + nextidvalue +  ", \'" +  e.getFirstName() + "\', \'" + 
					e.getLastName()+ "\',  \'" + e.getPassword()+ "\', \'" + e.getUsername()+ "\', " + nextidvalue + ", " + 0 + ")";
			
			//System.out.println(sql);
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			//System.out.println("Closing");
			rs.close();
			ps.close();
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		
	}

	public int getTableSize() {
		int size = 0;
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT COUNT(*) AS c FROM EMPLOYEEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next() ) {    
			    //System.out.println("Returning Size"); 
			    size = rs.getInt("c");
			}
		
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		
		return size;
	}

	public void submitForm(int rid, double amount) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REIMBURSEMENT SET TOTAL = " + amount + " WHERE RID = " + rid;
			//System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			//System.out.println("Closing");
			rs.close();
			ps.close();
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		
	}

	public boolean checkManager(int id) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT IS_MANAGER FROM EMPLOYEEE WHERE EMPLOYEEID = " + id;
			//System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int is_manager =  rs.getInt("IS_MANAGER");
			    if(is_manager == 1){
			    	rs.close();
					ps.close();
				    return true;
			    }
			    else{
			    	rs.close();
					ps.close();
			    	return false;
			    }
				
			} 
			else{
				//System.out.println("User does not exist. Please check your username or password"); 
				rs.close();
				ps.close();
				return false;
				
			}
			
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		return false;
	}

	public double getReimbursement(int id) {
		PreparedStatement ps = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			//System.out.println("Connection Successful");
			String sql = "SELECT TOTAL FROM REIMBURSEMENT WHERE RID = " + id;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				//System.out.println("hi");
				double total = rs.getDouble("TOTAL");
				//b = new Bear(id, name, age, weight);
				return total;
				
			}
			else{
				
				rs.close();
				ps.close();
				return 0.0;
			}
			
		}catch (Exception ex) {
			//System.out.println("Connection Unsuccessful");
			ex.getMessage();
			ex.printStackTrace();
		}
		return 0.0;
	}
	

}
