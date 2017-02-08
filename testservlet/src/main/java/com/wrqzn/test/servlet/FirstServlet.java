package com.wrqzn.test.servlet;


import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by WANG, RUIQING on 2/7/17
 * Twitter : @taylorwang789
 * E-mail : i@wrqzn.com
 */
public class FirstServlet extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.write("<h1> hello tw, first servlet!</h1>");
	}
}
