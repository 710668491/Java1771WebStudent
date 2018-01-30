package Student.Dao;

import java.util.List;

import Student.entity.Student;
import Student.entity.teacher;
import vo.StudentSearchCondition;
import vo.TeacherSearchCondition;

public interface IteacherDao {

	

	boolean checkName(String name);

	int add(teacher teacher);

	boolean delete(Integer id);

	int update(teacher teacher);

	

	List<teacher> findbyname(String name);

	List<teacher> searchByCondition(TeacherSearchCondition teacherSearchCondition);

	teacher findById(Integer id);

	

	List<teacher> findAll();


	
}
