package Student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Student.entity.Student;
import Student.entity.banji;
import Student.service.IBanjiService;
import Student.service.impl.banjiserviceimpl;
import vo.PageBean;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;

public class BanjiServlet extends BaseServlet {
	private IBanjiService banjiService = new banjiserviceimpl();
	
	public void checkName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		boolean isExist = banjiService.checkName(name);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"isExist\":"+isExist+"}");
	}




private void getbanjiAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("/WEB-INF/jsp/banji_add.jsp").forward(req, resp);
	
}

private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	int id = Integer.parseInt(req.getParameter("id"));
	String name = req.getParameter("name");
    banji banji = new banji(id, name);
	if (banjiService.update(banji)) {
		System.out.println("更新成功");
	} else {
		System.out.println("更新失败");
	}
	resp.sendRedirect(req.getContextPath() + "/banji?method=findbanji");
}

private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String idstr = req.getParameter("id");
	int id = Integer.parseInt(idstr);
	banji banji = banjiService.findById(id);
	req.setAttribute("banji", banji);
	req.getRequestDispatcher("/WEB-INF/jsp/banji_edit.jsp").forward(req, resp);
}



private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	Integer id = Integer.parseInt(req.getParameter("id"));
	boolean banji = banjiService.delete(id);
	resp.sendRedirect(req.getContextPath() + "/banji?method=findbanji");

}

private void findbyname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name = req.getParameter("name");
	List<banji> list = banjiService.findbyname(name);
	req.setAttribute("list", list);
	req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);

}

public void addbanji(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	String name = req.getParameter("name");
	banji banji = new banji(name);
	int result = banjiService.addbanji(banji);
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
	
	
	resp.sendRedirect(req.getContextPath() + "/banji?method=findbanji");
}

public void findbanji(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	System.out.println("jkl");
	List<banji> list = banjiService.findbanji();
	req.setAttribute("list", list);
	//req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);
	resp.sendRedirect(req.getContextPath() + "/banji?method=pageList");

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
	
	PageBean pageBean = banjiService.getPageBean(pageNo, pageSize);
	System.out.println(pageBean);
	req.setAttribute("pageBean", pageBean);
	req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);
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
	
	banjiSearchCondition banjiSearchCondition= new banjiSearchCondition(pageNo, pageSize, name);
	PageBean<banji> pageBean = banjiService.searchByCondition(banjiSearchCondition);
	req.setAttribute("pageBean", pageBean);
	req.setAttribute("searchCondition", banjiSearchCondition);
	req.getRequestDispatcher("/WEB-INF/jsp/banji_list.jsp").forward(req, resp);
}

}
