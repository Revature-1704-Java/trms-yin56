package com.revature.controllers;


import com.revature.model.dao.EmployeeDAO;
import com.revature.model.dao.ReimbursementDAO;
import com.revature.model.beans.Employee;
import com.revature.model.beans.Reimbursement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ApprovedTableServlet")
public class ApprovedTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In Profile Servlet");
		HttpSession session = request.getSession(false);
		
		int eid = (int)session.getAttribute("id");		
		ReimbursementDAO dao = new ReimbursementDAO();

		List<Reimbursement> rlist = dao.getApprovedReimbursement(eid);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if(rlist == null) {
			out.write("<p> Something went wrong </p>");
		}
		else if (rlist.size() == 0) {
			out.write("<p>You have no approved reimbursments</p>");
		}
		else {
			//loop through list and print all reimbursemtns in a table
			Reimbursement r = null;
			out.write("<table>");
			out.write("	<tr>");
			out.write("		<th>ID</th>");
			out.write("		<th>Description</th>");
			out.write("		<th>Cost</th>");
			out.write("	</tr>");
			for(int i = 0; i < rlist.size(); i++ ) {
				r = rlist.get(i);
				
				out.write("	<tr>");
				out.write("		<td>" + r.getRID() +"</td>");
				out.write("		<td>" + r.getEvt_description() +"</td>");
				out.write("     <td>" + r.getCost()+  "</td>");
				out.write("	</tr>");
				
			}
			out.write("</table>");
			
		}
		//response.getWriter().write("<h1>Welcome, " + username.toUpperCase() + "</h1>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	
	
	protected String returnApprovalString(int type) {
		if(type == 1) {
			return "Pending";
		}
		else if(type == 2) {
			return "Denied";
		}
		else if(type == 3) {
			return "Approved";
		}
		
		return "";
	}

}
