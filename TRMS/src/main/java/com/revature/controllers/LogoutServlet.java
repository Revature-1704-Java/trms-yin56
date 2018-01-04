package com.revature.controllers;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            //response.setContentType("text/html");  
            //PrintWriter out=response.getWriter();  
               
              
            HttpSession session=request.getSession();  
            session.invalidate();  
            response.sendRedirect("./index.html");
            //out.print("You are successfully logged out!");  
              
            //out.close();  
            
            //response.sendRedirect("./index.html");
    }  
}  