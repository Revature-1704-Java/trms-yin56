package com.revature.controllers;


import com.revature.model.dao.EmployeeDAO;
import com.revature.model.beans.Employee;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
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
		
		int eid = (Integer) session.getAttribute("id");
		String username = (String) session.getAttribute("user");
		EmployeeDAO edao = new EmployeeDAO();
		Double cost = edao.getCost(eid);
		response.setContentType("text/html");
		response.getWriter().write("<h1>Welcome, " + username + "</h1>");
		response.getWriter().write("<h2> Your balance is $" + cost);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
