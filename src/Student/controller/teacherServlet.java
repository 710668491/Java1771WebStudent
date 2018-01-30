package Student.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Student.entity.teacher;
import Student.service.ITeacherService;
import Student.service.impl.Teacherserviceimpl;
import vo.TeacherSearchCondition;

public class teacherServlet extends BaseServlet {
	private ITeacherService teacherService = new Teacherserviceimpl();

	

	private void getteacherAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/teacher_add.jsp").forward(req, resp);
		
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String subject = req.getParameter("subject");
		teacher teacher = new teacher(Integer.parseInt(id), name,Integer.parseInt(age), gender, subject);
		if (teacherService.update(teacher)) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");
		}
		resp.sendRedirect(req.getContextPath() + "/teacher?method=findteacher");
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = req.getParameter("id");
		int id = Integer.parseInt(idstr);
		teacher teacher = teacherService.findById(id);
		req.setAttribute("teacher", teacher);
		req.getRequestDispatcher("/WEB-INF/jsp/teacher_edit.jsp").forward(req, resp);
	}

	

	private void deleteteacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		boolean teacher = teacherService.delete(id);
		resp.sendRedirect(req.getContextPath() + "/teacher?method=findteacher");

	}

	private void findbyname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		List<teacher> list = teacherService.findbyname(name);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/showInfo.do").forward(req, resp);

	}

	public void addteacher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String subject = req.getParameter("subject");
		teacher teacher = new teacher( name,Integer.parseInt(age), gender, subject);
		int result = teacherService.add(teacher);
		resp.setContentType("text/html;charset=utf-8");
//		PrintWriter printWriter = resp.getWriter();
//		if (result == Constant.ADD_SUCCESS) {
//			printWriter.println("添加成功");
//		} else if (result == Constant.ADD_NAME_REPEAT) {
//			printWriter.println("名字重复");
//		} else {
//			printWriter.println("添加失败");
//		}
//		printWriter.close();
		// 重定向
		
		//resp.sendRedirect("/WEB-INF/jsp/student_list.jsp");
		//resp.sendRedirect(req.getContextPath() + "/student?method=findstudent");
		resp.sendRedirect(req.getContextPath() + "/teacher?method=findteacher");
	}

	public void findteacher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<teacher> list = teacherService.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/jsp/teacher_list.jsp").forward(req, resp);

	}

	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("teacherMainServlet.searchByCondition()");
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		TeacherSearchCondition teacherSearchCondition = new TeacherSearchCondition(name, age, gender);
		List<teacher> list = teacherService.searchByCondition(teacherSearchCondition);
		req.setAttribute("list", list);
		req.setAttribute("searchCondition", teacherSearchCondition);
		req.getRequestDispatcher("/WEB-INF/jsp/teacher_list.jsp").forward(req, resp);
	}
}
