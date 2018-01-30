package Student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Student.entity.banji;
import Student.entity.course;
import Student.service.ICourseService;
import Student.service.impl.courseserviceimpl;
import vo.PageBean;
import vo.banjiSearchCondition;
import vo.courseSearchCondition;

public class CourseServlet extends BaseServlet {
	private ICourseService courseService = new courseserviceimpl();



private void getcourseAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("/WEB-INF/jsp/course_add.jsp").forward(req, resp);
	
}

private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	int id = Integer.parseInt(req.getParameter("id"));
	String name = req.getParameter("name");
    course course = new course(id, name);
	if (courseService.update(course)) {
		System.out.println("更新成功");
	} else {
		System.out.println("更新失败");
	}
	resp.sendRedirect(req.getContextPath() + "/course?method=findcourse");
}

private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String idstr = req.getParameter("id");
	int id = Integer.parseInt(idstr);
	course course = courseService.findById(id);
	req.setAttribute("course", course);
	req.getRequestDispatcher("/WEB-INF/jsp/course_edit.jsp").forward(req, resp);
}



private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	Integer id = Integer.parseInt(req.getParameter("id"));
	boolean banji = courseService.delete(id);
	resp.sendRedirect(req.getContextPath() + "/course?method=findcourse");

}

private void findbyname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name = req.getParameter("name");
	List<course> list = courseService.findbyname(name);
	req.setAttribute("list", list);
	req.getRequestDispatcher("/WEB-INF/jsp/course_list.jsp").forward(req, resp);

}

public void addcourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	String name = req.getParameter("name");
	course course = new course(name);
	int result = courseService.addcourse(course);
	resp.setContentType("text/html;charset=utf-8");
//	PrintWriter printWriter = resp.getWriter();
//	if (result == Constant.ADD_SUCCESS) {
//		printWriter.println("添加成功");
//	} else if (result == Constant.ADD_NAME_REPEAT) {
//		printWriter.println("名字重复");
//	} else {
//		printWriter.println("添加失败");
//	}
//	printWriter.close();
	// 重定向
	
	
	resp.sendRedirect(req.getContextPath() + "/course?method=findcourse");
}

public void findcourse(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	System.out.println("jkl");
	List<course> list = courseService.findcourse();
	req.setAttribute("list", list);
	//req.getRequestDispatcher("/WEB-INF/jsp/course_list.jsp").forward(req, resp);
	resp.sendRedirect(req.getContextPath() + "/course?method=pageList");

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
	
	PageBean pageBean = courseService.getPageBean(pageNo, pageSize);
	System.out.println(pageBean);
	req.setAttribute("pageBean", pageBean);
	req.getRequestDispatcher("/WEB-INF/jsp/course_list.jsp").forward(req, resp);
}


private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("StudentMainServlet.searchByCondition()");
	
	String pageNoStr = req.getParameter("pageNo");
	String pageSizeStr = req.getParameter("pageSize");
	int pageNo = 1;
	if (pageNoStr != null &&!"".equals(pageNoStr)) {
		pageNo = Integer.parseInt(pageNoStr);
	}
	int pageSize = 3;
	if (pageSizeStr != null && !"".equals(pageSizeStr)) {
		pageSize = Integer.parseInt(pageSizeStr);
	}
	String name = req.getParameter("name");
	
	courseSearchCondition courseSearchCondition= new courseSearchCondition(pageNo, pageSize, name);
	PageBean pageBean = courseService.searchByCondition(courseSearchCondition);
	req.setAttribute("pageBean", pageBean);
	req.setAttribute("searchCondition", courseSearchCondition);
	req.getRequestDispatcher("/WEB-INF/jsp/course_list.jsp").forward(req, resp);
}




}
