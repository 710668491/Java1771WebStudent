package Student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Student.entity.User;
import Student.service.IUserService;
import Student.service.impl.UserServiceImpl;

public class LoginServlet extends BaseServlet {
	private IUserService userService = new UserServiceImpl();
	public void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String checkCode = request.getParameter("checkCode");
		String checkCodeSession = (String) request.getSession().getAttribute("checkCodeSession");
		if (checkCode == null 
				|| "".equals(checkCode)
				|| !checkCode.equalsIgnoreCase(checkCodeSession)) {
			response.sendRedirect(request.getContextPath() + "/student?method=searchByCondition");
			return;
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = userService.login(name, password);
		if (user != null) {//登录成功
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			List<User> list = (List<User>) getServletContext().getAttribute("onLineUserList");
			list.add(user);
			response.sendRedirect(request.getContextPath() + "/student?method=searchByCondition");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/user?method=getLoginPage");
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.removeAttribute("user");
		List<User> list = (List<User>) getServletContext().getAttribute("onLineUserList");
		list.remove(user);
		response.sendRedirect(request.getContextPath() + "/login?method=getLoginPage");
	}
	
	
	
	
	
	
	/*@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String possword = req.getParameter("possword");
		if ("zhangsan".equals(name) && "123".equals(possword)) {
			HttpSession session = req.getSession();
			session.setAttribute("username", name);
			resp.sendRedirect(req.getContextPath() + "/student?method=findstudent");
		}else {
			req.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
		
		
	}*/
}
