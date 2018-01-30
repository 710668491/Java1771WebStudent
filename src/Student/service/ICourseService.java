package Student.service;

import java.util.List;

import Student.entity.banji;
import Student.entity.course;
import vo.PageBean;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;
import vo.courseSearchCondition;

public interface ICourseService {


	boolean update(course course);

	course findById(int id);

	boolean delete(Integer id);

	List<course> findbyname(String name);

	int addcourse(course course);

	List<course> findcourse();

	

	PageBean getPageBean(int pageNo, int pageSize);

	PageBean<course> searchByCondition(courseSearchCondition courseSearchCondition);

}
