package com.revature.model.dao;

import com.revature.model.beans.Employee;
import com.revature.model.beans.Reimbursement;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.util.ConnectionUtil;


public class ReimbursementDAO {
	//add employee to database/account creation
	
	public boolean addReimbursement(Reimbursement r, int e) {
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			String p1 = "(COST_AMT, EMPLOYEEID, SUPER_APR, DEPT_ARR, BENCO_APR, EVTTYPE_ID, LOCAT, DESCRIPT, DATE_START, EVT_TIME, REASON, GF_ID)";
			String p2 = "(" + r.getCost() + " , " + e + ", " + 0 + ", " + 0 + ", " + 0  + ", " + r.getEvtId() + " ,'" + r.getLocation() + 
				"', '" + r.getEvt_description() + "', to_date('" + r.getDate_started() + "', 'yyyy/mm/dd')" + ", '" + r.getEvt_time() + "', '" + r.getReason() + "', " + r.getGf_id() + ")";
			String sql = "INSERT INTO REIMBURSEMENT " + p1 + " VALUES " + p2;
					
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
	
	
	public List<Reimbursement> getReimbursement(int eid) {
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEEID =" 
					+ eid;
					
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {    
			    int ed = rs.getInt("EMPLOYEEID");
			    int rid = rs.getInt("RID");
			    
				rs.close();
				ps.close();
			    return null;
			} 
			//System.out.println("Closing");
			
			
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			
		}
		return null;
	}
}
