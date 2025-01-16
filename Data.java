package com.cddst.p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Data")
public class Data extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
    String s1=req.getParameter("id");
    int i1=Integer.parseInt(s1);
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_dtl","root","root");
    	
    	pstmt=con.prepareStatement("select * from employee_dtl where id=?");
    			pstmt.setInt(1, i1);
    			
        res=pstmt.executeQuery();
        PrintWriter writer=resp.getWriter();
        while(res.next()==true) {
        	writer.println(res.getString(2));
        	writer.println("----------");
        }
    }
    
    catch(Exception e) {
    	e.printStackTrace();
    }
}
}
