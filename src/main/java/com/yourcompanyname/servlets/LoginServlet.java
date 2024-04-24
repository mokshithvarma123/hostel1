package com.yourcompanyname.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yourcompanyname.util.DBConnection;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if ("admin".equals(username) && "password123".equals(password)) {
            // Authentication successful
            response.sendRedirect("personDetails.jsp");
        } else {
            // Authentication failed
            response.sendRedirect("login.html");
        }
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // Authentication successful, redirect to personDetails.jsp
                response.sendRedirect("personDetails.jsp");
            } else {
                // Authentication failed, show error message
                PrintWriter out = response.getWriter();
                out.println("<script>alert('Invalid username or password');</script>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
