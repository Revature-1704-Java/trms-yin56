package com.revature.controllers;


import com.revature.model.dao.EmployeeDAO;
import com.revature.model.dao.ReimbursementDAO;
import com.revature.model.beans.Employee;
import com.revature.model.beans.Reimbursement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);
		int eid = (int)session.getAttribute("id");
		
		String descript = request.getParameter("descript");
		String location = request.getParameter("location");
		String eventtype = request.getParameter("eventtype");
		String gradetype = request.getParameter("gradetype");
		String cost = request.getParameter("cost");
		String startday = request.getParameter("startday");
		String time = request.getParameter("time");
		String just = request.getParameter("just");
		
		System.out.println(eid);
		System.out.println(eid + descript + location + eventtype + gradetype + cost + startday + time + just);
		
		
		Reimbursement r = new Reimbursement(Double.parseDouble(cost), Integer.parseInt(eventtype), 
				location, descript, startday, time, just, Integer.parseInt(gradetype));
		ReimbursementDAO dao = new ReimbursementDAO();
		PrintWriter out = response.getWriter();
		if(dao.addReimbursement(r, eid)) {
			response.sendRedirect("./WelcomeServlet");
		}
		else {
			out.println("<p>Something went wrong. :(<p>");
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
