package Student.Dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import Student.Dao.IStudentDao;
import Student.entity.Student;
import Student.until.C3P0Util;
import Student.until.JDBCUntil;
import vo.StudentSearchCondition;

public class StudentDaoImpl implements IStudentDao {
	private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
	@Override
	public int add(Student student) {
		int count = 0;
		String sql = "INSERT INTO student(NAME,age,gender,address,addTime,birthday,banji_id) VALUES(?,?,?,?,?,?,?);";
		Object[] params = {student.getName(),student.getAge(),student.getGender(),
				student.getAddress(),student.getAddTime(),student.getBirthday(),student.getBanji()};
		try {
			count = queryRunner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int delete(Integer id) {
		int count = 0;
		String sql = "DELETE FROM student WHERE id=?;";
		Object[] params = {id};
		try {
			count = queryRunner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	}

	@Override 
	public int update(Student student) {
		int count = 0;
		try {
			String sql = "UPDATE student SET name=?,age=?,gender=?,address=? WHERE id=?;";
		    Object[] params = {student.getName(),student.getAge(),student.getGender(),
			student.getAddress(),student.getId()};
			count = queryRunner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	
	}

	@Override
	public Student findById(Integer id) {
		String sql = "SELECT * FROM student WHERE id=?;";
		Object[] params = {id};
		try {
			return queryRunner.query(sql, new BeanHandler<Student>(Student.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> findAll() {
		List<Student> list = null;
		try {
		String sql = "SELECT id,NAME,age,gender,address,addTime,birthday FROM student;";
		list = queryRunner.query(sql, new BeanListHandler<Student>(Student.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean checkName(String name) {
		Student student = null;
		try {
			String sql = "SELECT NAME FROM student WHERE NAME=?;";
		    Object[] params = {name};
			student = queryRunner.query(sql, new BeanHandler<Student>(Student.class),name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (student != null) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public List<Student> findbyname(String name) {
		List<Student>list = null;
		try {
			String sql = "SELECT id,NAME,age,gender,address,addTime,birthday FROM student WHERE NAME=?;";
		    Object [] params = {name};
			list = queryRunner.query(sql, new BeanListHandler<Student>(Student.class),name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Student> searchByCondition(StudentSearchCondition studentSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT id,NAME,age,gender,address,birthday,addTime FROM student where 1=1 ";
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (studentSearchCondition.getName() != null 
					&& !"".equals(studentSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + studentSearchCondition.getName() + "%");
			}
			if (studentSearchCondition.getAge() != null 
					&& !"".equals(studentSearchCondition.getAge())) {
				sql += " and age = ? ";
				conditionList.add(studentSearchCondition.getAge());
			}
			if (studentSearchCondition.getGender() != null 
					&& !"".equals(studentSearchCondition.getGender())) {
				sql += " and gender = ? ";
				conditionList.add(studentSearchCondition.getGender());
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
				String address = resultSet.getString("address");
				String gender = resultSet.getString("gender");
				Date birthday = resultSet.getDate("birthday");// java.sql.Date
				Date addTime = resultSet.getDate("addTime");// java.sql.Date
				Student student = new Student(id, name, age, gender, address, addTime, birthday);
				list.add(student);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<Student> findPageBeanList(int offset, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME,age,gender,address,birthday,addTime FROM student LIMIT ?,?;";
		List<Student> list = new ArrayList<Student>();
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
				Integer age = resultSet.getInt("age");
				String address = resultSet.getString("address");
				String gender = resultSet.getString("gender");
				Date birthday = resultSet.getDate("birthday");
				Date addTime = resultSet.getDate("addTime");
				Student student = new Student(id, name, age, gender, address, addTime, birthday);
				list.add(student);
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
		String sql = "select count(*) from student";
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
	public int getTotalCount(StudentSearchCondition studentSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) from student where 1=1 ";
		int count = 0;
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (studentSearchCondition.getName() != null 
					&& !"".equals(studentSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + studentSearchCondition.getName() + "%");
			}
			if (studentSearchCondition.getAge() != null 
					&& !"".equals(studentSearchCondition.getAge())) {
				sql += " and age = ? ";
				conditionList.add(studentSearchCondition.getAge());
			}
			if (studentSearchCondition.getGender() != null 
					&& !"".equals(studentSearchCondition.getGender())) {
				sql += " and gender = ? ";
				conditionList.add(studentSearchCondition.getGender());
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
	public List<Student> findPageBeanList(StudentSearchCondition studentSearchCondition) {
		String sql = "SELECT id,NAME,age,gender,address,birthday FROM student where 1=1 ";
		List conditionList = new ArrayList();

			if (studentSearchCondition.getName() != null 
					&& !"".equals(studentSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + studentSearchCondition.getName() + "%");
			}
			if (studentSearchCondition.getAge() != null 
					&& !"".equals(studentSearchCondition.getAge())) {
				sql += " and age = ? ";
				conditionList.add(studentSearchCondition.getAge());
			}
			if (studentSearchCondition.getGender() != null 
					&& !"".equals(studentSearchCondition.getGender())) {
				sql += " and gender = ? ";
				conditionList.add(studentSearchCondition.getGender());
			}
			
			sql += " limit ?,?";
			int offset = (studentSearchCondition.getPageNo() - 1) * studentSearchCondition.getPageSize();
			conditionList.add(offset);
			conditionList.add(studentSearchCondition.getPageSize());
			Object[] params = conditionList.toArray();
			List<Student> list = null;
			try {
				list = queryRunner.query(sql, new BeanListHandler<Student>(Student.class),params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	
	}
	@Override
	public boolean deleteAll(String[] ids) {
		
		try {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
			connection = JDBCUntil.getConnection();
			String sql = "delete from student where id=?";
			preparedStatement = connection.prepareStatement(sql);
			for (String id : ids) {
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.addBatch();
			}
			int[] results = preparedStatement.executeBatch();
			if (results.length == ids.length) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}


