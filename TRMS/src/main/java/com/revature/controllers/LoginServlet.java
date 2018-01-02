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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/*
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		
		
		
		EmployeeDAO dao = new EmployeeDAO();
		//Employee e = new Employee(fname, lname, password, username, email);
		PrintWriter out = response.getWriter();
		if(dao.checkEmployee(username, password) != 0) {
			out.println("<p>Login Successful");
			session.setAttribute("user", username);
		    response.sendRedirect("./WelcomeServlet");
			
		}
		else {
			out.println("<p>Something went wrong. :(<p>");
		}
		out.close();
		
		
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
