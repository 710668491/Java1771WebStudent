package Student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import Student.entity.Student;
import Student.service.IBanjiService;
import Student.service.IStudentService;
import Student.service.impl.Studentserviceimpl;
import Student.service.impl.banjiserviceimpl;

public class BaseServlet extends HttpServlet {

	private IStudentService studentService = new Studentserviceimpl();
	//private IBanjiService banjiserviceimpl = new banjiserviceimpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		/*String username = (String) session.getAttribute("username");
		if (username == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			return;
		}*/
		System.out.println(req.getRequestURI());
		System.out.println(req.getContextPath());
		String servletPath = req.getServletPath();
		System.out.println(servletPath);                                                                                                                                                                                                                                                                                                                                                                                                               
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String methodName = req.getParameter("method");
		Class clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(methodName, new Class[]{HttpServletRequest.class, HttpServletResponse.class});
			method.setAccessible(true);
			method.invoke(this, new Object[]{req,resp});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
