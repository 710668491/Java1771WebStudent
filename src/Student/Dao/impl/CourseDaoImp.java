package Student.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Student.Dao.IcourseDao;
import Student.entity.banji;
import Student.entity.course;
import Student.until.JDBCUntil;
import vo.banjiSearchCondition;
import vo.courseSearchCondition;

public class CourseDaoImp implements IcourseDao {
	@Override
	public int addcourse(course course) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO course(NAME) VALUES(?);";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, course.getName());;
			int result= preparedStatement.executeUpdate();
			return result;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement);
		}
		
		return 0;
	}

	@Override
	public boolean delete(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM course WHERE id=?;";
		boolean result = false;
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int count = preparedStatement.executeUpdate();
			if (count >0) {
				result = true;
				
			}else {
				result = false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public int update(course course) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		String sql = "UPDATE course SET name=? WHERE id=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getId());
			

			count = preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		 

		return count;
	
	}


	@Override
	public List<course> findcourse() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM course;";
		List<course> list = new ArrayList<course>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				
				course course = new course(id, name);
				list.add(course);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement , resultSet);
		}
		
		
		return list;
	}

	@Override
	public boolean checkName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String sql = "SELECT NAME FROM course WHERE NAME=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement, resultSet);
		}

		return false;
	}

	@Override
	public List<course> findbyname(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM course WHERE NAME = ?;";
		List<course> list = new ArrayList<course>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name1 = resultSet.getString("name");

				course course = new course(id, name1);
				list.add(course);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement , resultSet);
		}
		
		
		return list;
	}

	public List<course> searchByCondition(courseSearchCondition courseSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<course> list = new ArrayList<course>();
		String sql = "SELECT id,NAME FROM course where 1=1 ";
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			
		
			if (courseSearchCondition.getName() != null 
					&& !"".equals(courseSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + courseSearchCondition.getName() + "%");
			}
		
			//sql语句拼接好
			preparedStatement = connection.prepareStatement(sql);
			//给占位符?赋值
			for (int i = 0; i < conditionList.size(); i++) {
				preparedStatement.setObject(i + 1, conditionList.get(i));
			}

			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				course course = new course(id, name);
				list.add(course);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}





	@Override
	public course findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		course course = null;
		String sql = "SELECT * FROM course WHERE id=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String Id = resultSet.getString("id");
				String name = resultSet.getString("name");
				course = new course(id, name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement);
		}
		return course;
	
	}

	@Override
	public int getTotalCount(courseSearchCondition courseSearchCondition) {


		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) from course where 1=1 ";
		int count = 0;
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (courseSearchCondition.getName() != null 
					&& !"".equals(courseSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + courseSearchCondition.getName() + "%");
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
	public List<course> findPageBeanList(courseSearchCondition courseSearchCondition) {


		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM course where 1=1 ";
		List<course> list = new ArrayList<course>();
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (courseSearchCondition.getName() != null 
					&& !"".equals(courseSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + courseSearchCondition.getName() + "%");
			}


			
			sql += " limit ?,?";
			
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < conditionList.size(); i++) {
				preparedStatement.setObject(i + 1, conditionList.get(i));
			}
			
			int offset = (courseSearchCondition.getPageNo() - 1) * courseSearchCondition.getPageSize();
			preparedStatement.setInt(conditionList.size() + 1, offset);
			preparedStatement.setInt(conditionList.size() + 2, courseSearchCondition.getPageSize());
			
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				

				course course = new course(id, name);
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUntil.close(connection, preparedStatement, resultSet);
		}
		return list;
	
	
	
	}

	@Override
	public int getTotalCount() {


		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) from course";
		int count = 0;
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
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
	public List<course> findPageBeanList(int offset, int pageSize) {


		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM course LIMIT ?,?;";
		List<course> list = new ArrayList<course>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name"); 
				course course = new course(id, name);
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			JDBCUntil.close(connection, preparedStatement, resultSet);
		}
		return list;
	
	
		
	}



		

}


