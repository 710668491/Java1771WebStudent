package Student.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import Student.Dao.IManagerDao;
import Student.entity.Student;
import Student.until.C3P0Util;
import Student.until.JDBCUntil;
import Student.until.ModelConvert;
import vo.ManagerSearchCondition;

public class ManagerDaoImpl implements IManagerDao {
	private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());


/*	@Override
	public List<Map<String, Object>> findAllByJdbc() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			connection = JDBCUntil.getConnection();
			String sql = "SELECT s.name  AS s_name,age,b.name AS b_name,c.name AS c_name,c.credit "
					+ "FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id "
					+ "INNER JOIN banji_course AS bc ON b.id=bc.banji_id "
					+ "INNER JOIN course AS c ON bc.course_id=c.id;";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.converList(resultSet);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}*/

	@Override
	public int getTotalCount(ManagerSearchCondition managerSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) "
				+ "from student AS s INNER JOIN banji AS b ON s.banji_id=b.id "
				+ "INNER JOIN banji_course AS bc ON b.id=bc.banji_id "
				+ "INNER JOIN course AS c ON bc.course_id=c.id ";
				//+ "where 1=1 ";
		int count = 0;
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (managerSearchCondition.getName() != null 
					&& !"".equals(managerSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + managerSearchCondition.getName() + "%");
			}
			if (managerSearchCondition.getAge() != null 
					&& !"".equals(managerSearchCondition.getAge())) {
				sql += " and age = ? ";
				conditionList.add(managerSearchCondition.getAge());
			}
			if (managerSearchCondition.getBanji() != null 
					&& !"".equals(managerSearchCondition.getBanji())) {
				sql += " and banji = ? ";
				conditionList.add(managerSearchCondition.getBanji());
			}
			if (managerSearchCondition.getCourse() != null 
					&& !"".equals(managerSearchCondition.getCourse())) {
				sql += " and course = ? ";
				conditionList.add(managerSearchCondition.getCourse());
			}
			if (managerSearchCondition.getCredit() != null 
					&& !"".equals(managerSearchCondition.getCredit())) {
				sql += " and credit = ? ";
				conditionList.add(managerSearchCondition.getCredit());
			}
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < conditionList.size(); i++) {
				preparedStatement.setObject(i + 1, conditionList.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUntil.close(connection, preparedStatement, resultSet);
		}
		return count;
	}
	
	

	@Override
	public List<Map<String, Object>> findPageBeanList(ManagerSearchCondition managerSearchCondition) {
		String sql = "SELECT s.name  AS s_name,age,b.name AS b_name,c.name AS c_name,c.credit "
					+ "FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id "
					+ "INNER JOIN banji_course AS bc ON b.id=bc.banji_id "
					+ "INNER JOIN course AS c ON bc.course_id=c.id "
					+ "where 1=1 ";
		List conditionList = new ArrayList();

			if (managerSearchCondition.getName() != null 
					&& !"".equals(managerSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + managerSearchCondition.getName() + "%");
			}
			if (managerSearchCondition.getAge() != null 
					&& !"".equals(managerSearchCondition.getAge())) {
				sql += " and age = ? ";
				conditionList.add(managerSearchCondition.getAge());
			}
			if (managerSearchCondition.getBanji() != null 
					&& !"".equals(managerSearchCondition.getBanji())) {
				sql += " and gender = ? ";
				conditionList.add(managerSearchCondition.getBanji());
			}
			if (managerSearchCondition.getCourse() != null 
					&& !"".equals(managerSearchCondition.getCourse())) {
				sql += " and gender = ? ";
				conditionList.add(managerSearchCondition.getCourse());
			}
			if (managerSearchCondition.getCredit()!= null 
					&& !"".equals(managerSearchCondition.getCredit())) {
				sql += " and gender = ? ";
				conditionList.add(managerSearchCondition.getCredit());
			}
			sql += " limit ?,?";
			int offset = (managerSearchCondition.getPageNo() - 1) * managerSearchCondition.getPageSize();
			conditionList.add(offset);
			conditionList.add(managerSearchCondition.getPageSize());
			Object[] params = conditionList.toArray();
			List<Map<String, Object>> list = null;
			try {
				list = queryRunner.query(sql, new MapListHandler(),params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	
	}

}
