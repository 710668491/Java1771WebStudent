package Student.Dao;

import java.util.List;

import Student.entity.Student;
import Student.entity.banji;
import Student.entity.course;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;
import vo.courseSearchCondition;

public interface IcourseDao {


	public abstract int addcourse(course course);

	public abstract boolean checkName(String name);

	public abstract boolean delete(Integer id);

	public abstract int update(course course);

	public abstract course findById(Integer id);

	public abstract List<course> findbyname(String name);

	public abstract List<course> findcourse();

	public abstract int getTotalCount(courseSearchCondition courseSearchCondition);

	public abstract List<course> findPageBeanList(courseSearchCondition courseSearchCondition);

	public abstract int getTotalCount();

	public abstract List<course> findPageBeanList(int offset, int pageSize);

	



}



