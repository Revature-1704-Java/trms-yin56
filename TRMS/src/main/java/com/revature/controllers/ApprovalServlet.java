package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.beans.Reimbursement;
import com.revature.model.dao.EmployeeDAO;
import com.revature.model.dao.ReimbursementDAO;


@WebServlet("/ApprovalServlet")
public class ApprovalServlet  extends HttpServlet{
private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);
		//session.setAttribute(arg0, arg1);
		int eid = (int)session.getAttribute("id");
		String rid = request.getParameter("approve");
		
		EmployeeDAO dao = new EmployeeDAO();
		int type = dao.getEmployeeType(eid);
		
		ReimbursementDAO dao2 = new ReimbursementDAO();
		System.out.println("approving");
		if(dao2.updateReimbursement(3, Integer.parseInt(rid), type)) {
			//if all 3 has been approved, then subtract from available
			System.out.println("updating left");
			if(dao2.checkAllApproved(Integer.parseInt(rid))) {
				System.out.println("updating left cost");
				int e = dao2.getEmployeeWithRID(Integer.parseInt(rid));
				double left = dao.getCost(e);
				double cost = dao2.getCost(Integer.parseInt(rid));
				double res = left - cost;
				dao.updateAmtLeft(e, res);
				
			}
			
			
			
			response.sendRedirect("./WelcomeServlet");
		}
		
		
		
		
		
		
		/*
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<p>Your name is: ");
		out.println(fname + " " + lname + "</p>");
		out.println("</br><p>Your email: " + email + "</p>");
		out.println("</br><p>Your password: " + password + "</p>");
		out.close();
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
}
