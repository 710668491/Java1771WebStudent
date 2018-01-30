package Student.Dao;

import java.util.List;

import Student.entity.Student;
import vo.StudentSearchCondition;

public interface IStudentDao {
	public abstract int add(Student student);
	
	public abstract  int delete(Integer id);
	
	public abstract int update(Student student);
	
	public abstract Student findById(Integer id);
	
	public abstract List<Student> findAll();
	
	public abstract boolean checkName(String name);

	public abstract List<Student> findbyname(String name);

	public abstract List<Student> searchByCondition(StudentSearchCondition studentSearchCondition);

	public abstract List<Student> findPageBeanList(int offset, int pageSize);

	public abstract int getTotalCount();

	public abstract int getTotalCount(StudentSearchCondition studentSearchCondition);

	public abstract List<Student> findPageBeanList(StudentSearchCondition studentSearchCondition);

	public abstract boolean deleteAll(String[] ids);

}
