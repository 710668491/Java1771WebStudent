package Student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import Student.entity.Student;

import Student.service.IManagerService;
import Student.service.impl.ManagerServiceImpl;
import Student.until.Constant;
import vo.ManagerSearchCondition;
import vo.PageBean;
import vo.StudentSearchCondition;

/**
 * Servlet implementation class ManagerMainServlet
 */
public class ManagerMainServlet extends BaseServlet {
	private IManagerService managerService = new ManagerServiceImpl();
	
	/*public void getManagerPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = managerService.findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(request, response);
	}*/
	
	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ManagerMainServlet.searchByCondition()");
		
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
		String banji = req.getParameter("banji");
		String course = req.getParameter("course");
		String credit = req.getParameter("credit");
		ManagerSearchCondition managerSearchCondition = new ManagerSearchCondition(pageNo, pageSize, name, age, banji, course, credit);
		PageBean pageBean = managerService.searchByCondition(managerSearchCondition);
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("searchCondition", managerSearchCondition);
		req.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(req, resp);
	}
}
