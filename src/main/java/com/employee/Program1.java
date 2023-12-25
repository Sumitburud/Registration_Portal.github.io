package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Program1 extends HttpServlet{
	
	
	 Connection connection = null;
	
	String url="jdbc:mysql://localhost:3306/jdbcclass";
	String username="root";
	String password ="Sumit@123";
	
	 
	
	
	@Override
	public void init() throws ServletException {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		
		try {
			 connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("username");
		String email = req.getParameter("useremail");
		String phone = req.getParameter("usernumber");
		String dob = req.getParameter("userdob");
		
		
		 try {
			 
			 String SQL1 ="INSERT into `registrationportal` (`Name`,`Email`,`Phone`,`DOB`) values(?,?,?,?)";
	
			 PreparedStatement pstatement = connection.prepareStatement(SQL1);
			
			pstatement.setString(1, name);
			pstatement.setString(2, email);
			pstatement.setString(3, phone);
			pstatement.setString(4, dob);
			
			int i = pstatement.executeUpdate();
			
			String SQL2 = "SELECT * from `registrationportal`";
			  Statement statement = connection.createStatement();
			 
			 ResultSet res = statement.executeQuery(SQL2);
			 
			
			while(res.next()==true) {
				
				
				String Name = res.getString("Name");
				String Email = res.getString("Email");
				String Phone = res.getString("Phone");
				String DOB = res.getString("DOB");
				
				out.println("_____________________________________________________________________________");
				
				out.printf("| %-20s | %-25s | %-10s | %-10s | \n",Name,Email,Phone,DOB);
				
			}
			out.println(" _____________________________________________________________________________");
			
			
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			
		
		
}
	
	
	

	
	@Override
	public void destroy() {
		

	}
	
	
	
	
	
}
	
	
	





 








