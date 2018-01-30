package Student.Dao.impl;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Student.Dao.IbanjiDao;
import Student.entity.Student;
import Student.entity.banji;
import Student.until.JDBCUntil;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;

public class BanjiDaoImp implements IbanjiDao {
	@Override
	public int addbanji(banji banji) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO banji(NAME) VALUES(?);";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());;
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
		String sql = "DELETE FROM banji WHERE id=?;";
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
	public int update(banji banji) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		String sql = "UPDATE banji SET name=? WHERE id=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());
			preparedStatement.setInt(2, banji.getId());
			

			count = preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		 

		return count;
	
	}


	@Override
	public List<banji> findbanji() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji;";
		List<banji> list = new ArrayList<banji>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				
				banji banji = new banji(id, name);
				list.add(banji);
				
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
		String sql = "SELECT NAME FROM banji WHERE NAME=?;";
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
	public List<banji> findbyname(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji WHERE NAME = ?;";
		List<banji> list = new ArrayList<banji>();
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name1 = resultSet.getString("name");

				banji banji = new banji(id, name1);
				list.add(banji);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement , resultSet);
		}
		
		
		return list;
	}

	public List<banji> searchByCondition(banjiSearchCondition banjiSearchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<banji> list = new ArrayList<banji>();
		String sql = "SELECT id,NAME FROM banji where 1=1 ";
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			
		
			if (banjiSearchCondition.getName() != null 
					&& !"".equals(banjiSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + banjiSearchCondition.getName() + "%");
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

				banji banji = new banji(id, name);
				list.add(banji);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}



	@Override
	public banji findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		banji banji = null;
		String sql = "SELECT * FROM banji WHERE id=?;";
		try {
			connection = JDBCUntil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String Id = resultSet.getString("id");
				String name = resultSet.getString("name");
				banji = new banji(id, name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUntil.close(connection, preparedStatement);
		}
		return banji;
	}

	@Override
	public int getTotalCount(banjiSearchCondition banjiSearchCondition) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) from banji where 1=1 ";
		int count = 0;
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (banjiSearchCondition.getName() != null 
					&& !"".equals(banjiSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + banjiSearchCondition.getName() + "%");
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
	public List<banji> findPageBeanList(banjiSearchCondition banjiSearchCondition) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji where 1=1 ";
		List<banji> list = new ArrayList<banji>();
		List<String> conditionList = new ArrayList<String>();
		try {
			connection = JDBCUntil.getConnection();
			if (banjiSearchCondition.getName() != null 
					&& !"".equals(banjiSearchCondition.getName())) {
				sql += " and name like ? ";
				conditionList.add("%" + banjiSearchCondition.getName() + "%");
			}


			
			sql += " limit ?,?";
			
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < conditionList.size(); i++) {
				preparedStatement.setObject(i + 1, conditionList.get(i));
			}
			
			int offset = (banjiSearchCondition.getPageNo() - 1) * banjiSearchCondition.getPageSize();
			preparedStatement.setInt(conditionList.size() + 1, offset);
			preparedStatement.setInt(conditionList.size() + 2, banjiSearchCondition.getPageSize());
			
			resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				

				banji banji = new banji(id, name);
				list.add(banji);
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
		String sql = "select count(*) from banji";
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
	public List<banji> findPageBeanList(int offset, int pageSize) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,NAME FROM banji LIMIT ?,?;";
		List<banji> list = new ArrayList<banji>();
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
				banji banji = new banji(id, name);
				list.add(banji);
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


