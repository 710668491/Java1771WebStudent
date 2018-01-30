package Student.service;

import java.util.List;

import Student.entity.teacher;
import vo.TeacherSearchCondition;

public interface ITeacherService {

	boolean update(teacher teacher);

	teacher findById(int id);

	boolean delete(Integer id);

	List<teacher> findbyname(String name);

	int add(teacher teacher);

	List<teacher> findAll();

	List<teacher> searchByCondition(TeacherSearchCondition teacherSearchCondition);

	

	
	
}
