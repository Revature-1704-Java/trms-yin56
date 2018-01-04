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
	
	
	public List<Reimbursement> getAllReimbursement(int eid) {
		PreparedStatement ps = null;
		Reimbursement re = null;
		List<Reimbursement> rlist = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEEID =" + eid; 
		
					
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {    
			    int rid = rs.getInt("RID");
			    String descript = rs.getString("DESCRIPT");
			    int dsApr = rs.getInt("SUPER_APR");
			    int dhApr = rs.getInt("DEPT_ARR");
			    int bencoApr = rs.getInt("BENCO_APR");
			    
			    re = new Reimbursement(rid, descript, dsApr, dhApr, bencoApr);
			    rlist.add(re);
			    
				
			} 
			rs.close();
			ps.close();
		    return rlist;
			//System.out.println("Closing");
			
			
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			
		}
		return null;
	}
	
	
	public List<Reimbursement> getAllPendingReimbursement(int eid, int type) {
		PreparedStatement ps = null;
		Reimbursement re = null;
		List<Reimbursement> rlist = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Database connection successful");
			
			//first get employees where their manager type is the given
			String mantype = "";
			if(type == 2) {
				mantype = "SUPERVISOR";
			}
			else if(type ==3) {
				mantype = "DEPT_HEAD";
			}
			else if(type  == 4) {
				mantype = "BENCO";
			}
			String sql1 = "SELECT EMPLOYEEID FROM EMPLOYEE WHERE " + mantype + " = " + eid;
			ps = conn.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			
			//put all the ids into a list, will use this for the next query
			List<Integer> elist = new ArrayList<>();
			int neweid;
			while(rs.next()) {
				neweid = rs.getInt("EMPLOYEEID");
				elist.add(neweid);
			}
			
			//if none return empty list
			if(elist.size() == 0) {
				return rlist;
			}
			
			
			for(int i = 0; i < elist.size(); i++) {
				String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEEID = " + elist.get(i);
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					int rid = rs.getInt("RID");
				    String descript = rs.getString("DESCRIPT");
				    int dsApr = rs.getInt("SUPER_APR");
				    int dhApr = rs.getInt("DEPT_ARR");
				    int bencoApr = rs.getInt("BENCO_APR");
				    re = new Reimbursement(rid, descript, dsApr, dhApr, bencoApr);
				    rlist.add(re);
				}
			}
			
		
			rs.close();
			ps.close();
		    return rlist;
			//System.out.println("Closing");
			
			
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			
		}
		return null;
	}
	
	//0 pending, 2 denied, 3 approved
	public boolean updateReimbursement(int status, int rid, int type) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String t = "";
			if(type == 2) {
				t = "SUPER_APR";	
			}
			else if(type == 3) {
				t = "DEPT_ARR";
			}
			else if (type == 4) {
				t = "BENCO_APR";
			}
			
			String sql = "UPDATE REIMBURSEMENT SET " + t + " = " + status + " WHERE RID = " + rid;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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
