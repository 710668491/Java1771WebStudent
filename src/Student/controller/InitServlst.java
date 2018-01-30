package Student.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import Student.until.JDBCUntil;

public class InitServlst extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("InitServlet.init()");
		ServletContext servletContext = getServletContext();
		JDBCUntil.init(servletContext);
		
		
		servletContext.setAttribute("count", 0);
		
	}
}
