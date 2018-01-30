package Student.service;

import java.util.List;

import Student.entity.Student;
import vo.PageBean;
import vo.StudentSearchCondition;

public interface IStudentService {
	List<Student> findAll();	
	
	int add(Student student);
	
	boolean delete(Integer id);
	
	boolean update( Student student);
	
	
	List<Student> findbyname(String name);

	
	Student findById(int id);

	PageBean getPageBean(int pageNo, int pageSize);

	PageBean<Student> searchByCondition(StudentSearchCondition studentSearchCondition);

	boolean checkName(String name);

	boolean deleteAll(String[] ids);
	
	
}
