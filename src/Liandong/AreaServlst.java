package Liandong;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import Student.until.C3P0Util;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class AreaServlst
 */
public class AreaServlst extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String areaId = req.getParameter("aredId");
		QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select id,area,city_id from tm_area where city_id=?";
		Object[] params = {areaId};
		try {
			List<Area> list = queryRunner.query(sql, new BeanListHandler<Area>(Area.class), params);
			JSONArray jsonArray = JSONArray.fromObject(list);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonArray.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
