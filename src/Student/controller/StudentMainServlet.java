package Student.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Student.entity.Student;
import Student.entity.banji;
import Student.service.IBanjiService;
import Student.service.IStudentService;
import Student.service.impl.Studentserviceimpl;
import Student.service.impl.banjiserviceimpl;
import Student.until.Constant;
import vo.PageBean;
import vo.StudentSearchCondition;

public class StudentMainServlet extends BaseServlet {
	private IStudentService studentService = new Studentserviceimpl();
	private IBanjiService banjiService = new banjiserviceimpl();
	
	public void findbanji(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("jkl");
		List<banji> list = banjiService.findbanji();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(req, resp);
		//resp.sendRedirect(req.getContextPath() + "/banji?method=pageList");

	}
	
	public void checkName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		boolean isExist = studentService.checkName(name);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"isExist\":"+isExist+"}");
	}

	private void getStduentAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(req, resp);
		
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		Student student = new Student(Integer.parseInt(idStr), name, Integer.parseInt(age), gender, address, new Date(), new Date());
		if (studentService.update(student)) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");
		}
		resp.sendRedirect(req.getContextPath() + "/student?method=findstudent");
	}

	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idstr = req.getParameter("id");
		int id = Integer.parseInt(idstr);
		Student student = studentService.findById(id);
		req.setAttribute("student", student);
		req.getRequestDispatcher("/WEB-INF/jsp/student_edit.jsp").forward(req, resp);
	}

	

	private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		boolean student = studentService.delete(id);
		resp.sendRedirect(req.getContextPath() + "/student?method=findstudent");

	}

	private void findbyname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		List<Student> list = studentService.findbyname(name);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/showInfo.do").forward(req, resp);

	}

	public void addstudnet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String banjiId = req.getParameter("banjiId");
		Student student = new Student(name, Integer.parseInt(age), gender, address, new Date(), new Date(),banjiId);
		int result = studentService.add(student);
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
		resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");
	}

	public void findstudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<Student> list = studentService.findAll();
		req.setAttribute("list", list);
		//req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/student?method=searchByCondition");

	}
	private void pageList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String pageNoStr = req.getParameter("pageNo");
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1;
		if (pageNoStr != null && !"".equals(pageNoStr)) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		int pageSize = 3;
		if (pageSizeStr != null && !"".equals(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		PageBean pageBean = studentService.getPageBean(pageNo, pageSize);
		System.out.println(pageBean);
		req.setAttribute("pageBean", pageBean);
		req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
	}
	

	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("StudentMainServlet.searchByCondition()");
		
		String pageNoStr = req.getParameter("pageNo");
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 1;
		if (pageNoStr != null &&!"".equals(pageNoStr)) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		int pageSize = Constant.DEFAULT_PAGE_SIZE;
		if (pageSizeStr != null && !"".equals(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		StudentSearchCondition studentSearchCondition = new StudentSearchCondition(pageNo, pageSize, name, age, gender);
		PageBean<Student> pageBean = studentService.searchByCondition(studentSearchCondition);
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("searchCondition", studentSearchCondition);
		req.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp").forward(req, resp);
	}
	
	public void deleteAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("selectIds");
		for (String string : ids) {
			System.out.println(string);
		}
		studentService.deleteAll(ids);
		response.sendRedirect(request.getContextPath() + "/student?method=searchByCondition");
	}
}
