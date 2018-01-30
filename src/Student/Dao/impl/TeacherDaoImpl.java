package Student.Dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Student.Dao.IStudentDao;
import Student.Dao.IteacherDao;
import Student.entity.Student;
import Student.entity.teacher;
import Student.until.JDBCUntil;
import vo.StudentSearchCondition;
import vo.TeacherSearchCondition;

public class TeacherDaoImpl implements  IteacherDao {
	@Override
	public int add(teacher teacher) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO teacher(NAME,age,gender,subject) VALUES(?,?,?,?);";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setInt(2, teacher.getAge());
			preparedStatement.setString(3, teacher.getGender());
			preparedStatement.setString(4, teacher.getSubject());
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
		String sql = "DELETE FROM teacher WHERE id=?;";
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
	public int update(teacher teacher) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		String sql = "UPDATE teacher SET name=?,age=?,gender=?,subject=? WHERE id=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setInt(2, teacher.getAge());
			preparedStatement.setString(3, teacher.getGender());
			preparedStatement.setString(4, teacher.getSubject());
			preparedStatement.setInt(5, teacher.getId());
			count = preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		 

		return count;
	
	}

	@Override
	public teacher findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		teacher teacher = null;
		String sql = "SELECT * FROM teacher WHERE id=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int Id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String subject = resultSet.getString("subject");
				teacher = new teacher(id, name, age, gender, subject);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement);
		}
		return teacher;
	}


	@Override
	public boolean checkName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String sql = "SELECT NAME FROM teacher WHERE NAME=?;";
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
	public List<teacher> findbyname(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME,age,gender,subject FROM teacher WHERE NAME=?;";
		List<teacher> list = new ArrayList<teacher>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name1 = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
			    String subject = resultSet.getString("subject");
			    teacher teacher = new teacher(id, name1, age, gender, subject);
				list.add(teacher);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement , resultSet);
		}
		
		
		return list;
	}

	@Override
	public List<teacher> searchByCondition(TeacherSearchCondition teacherSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<teacher> list = new ArrayList<teacher>();
		String sql = "SELECT id,NAME,age,gender,sunject FROM teacher where 1=1 ";
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (teacherSearchCondition.getName() != null 
					&& !"".equals(teacherSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + teacherSearchCondition.getName() + "%");
			}
			if (teacherSearchCondition.getAge() != null 
					&& !"".equals(teacherSearchCondition.getAge())) {
				sql += " and age = ? ";
				conditionList.add(teacherSearchCondition.getAge());
			}
			if (teacherSearchCondition.getGender() != null 
					&& !"".equals(teacherSearchCondition.getGender())) {
				sql += " and gender = ? ";
				conditionList.add(teacherSearchCondition.getGender());
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
				Integer age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
			    String subject = resultSet.getString("subject");
			    teacher teacher = new teacher(id, name, age, gender, subject);
				list.add(teacher);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<teacher> findAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME,age,gender,subject FROM teacher;";
		List<teacher> list = new ArrayList<teacher>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String subject = resultSet.getString("subject");
				teacher teacher = new teacher(id, name, age, gender, subject);
				list.add(teacher);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement , resultSet);
		}
		
		
		return list;
	}


}
