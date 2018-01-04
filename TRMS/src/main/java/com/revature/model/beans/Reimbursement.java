package com.revature.model.beans;

public class Reimbursement {
	private int RID;
	private double cost;
	private int EID;
	private int superApproved;
	private int deptApproved;
	private int bencoApproved;
	private int evtId;
	private String location;
	private String evt_description;
	private String date_started;
	private String evt_time;
	public int getRID() {
		return RID;
	}
	public void setRID(int rID) {
		RID = rID;
	}
	public double getCost() {
		return cost;
	}
	public Reimbursement(double cost, int evtId, String location, String evt_description, String date_started,
			String evt_time, String reason, int gf_id) {
		super();
		this.cost = cost;
		this.evtId = evtId;
		this.location = location;
		this.evt_description = evt_description;
		this.date_started = date_started;
		this.evt_time = evt_time;
		this.reason = reason;
		this.gf_id = gf_id;
	}
	public Reimbursement(int rid, String evt_description, int superApproved, int deptApproved, int bencoApproved) {
		this.RID = rid;
		this.evt_description = evt_description;
		this.superApproved = superApproved;
		this.deptApproved = deptApproved;
		this.bencoApproved = bencoApproved;
		
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getEID() {
		return EID;
	}
	public void setEID(int eID) {
		EID = eID;
	}
	public int getSuperApproved() {
		return superApproved;
	}
	public void setSuperApproved(int superApproved) {
		this.superApproved = superApproved;
	}
	public int getDeptApproved() {
		return deptApproved;
	}
	public void setDeptApproved(int deptApproved) {
		this.deptApproved = deptApproved;
	}
	public int getBencoApproved() {
		return bencoApproved;
	}
	public void setBencoApproved(int bencoApproved) {
		this.bencoApproved = bencoApproved;
	}
	public int getEvtId() {
		return evtId;
	}
	public void setEvtId(int evtId) {
		this.evtId = evtId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEvt_description() {
		return evt_description;
	}
	public void setEvt_description(String evt_description) {
		this.evt_description = evt_description;
	}
	public String getDate_started() {
		return date_started;
	}
	public void setDate_started(String date_started) {
		this.date_started = date_started;
	}
	public String getEvt_time() {
		return evt_time;
	}
	public void setEvt_time(String evt_time) {
		this.evt_time = evt_time;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	public int getGf_id() {
		return gf_id;
	}
	public void setGf_id(int gf_id) {
		this.gf_id = gf_id;
	}
	private String reason;
	private double hours;
	private int gf_id;
	
	
	
}
