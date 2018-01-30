package Student.service.impl;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import Student.Dao.IStudentDao;
import Student.Dao.IbanjiDao;
import Student.Dao.IcourseDao;
import Student.Dao.impl.BanjiDaoImp;
import Student.Dao.impl.CourseDaoImp;
import Student.Dao.impl.StudentDaoImpl;
import Student.entity.Student;
import Student.entity.banji;
import Student.entity.course;
import Student.service.IBanjiService;
import Student.service.ICourseService;
import Student.service.IStudentService;
import Student.until.Constant;
import vo.PageBean;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;
import vo.courseSearchCondition;

public class courseserviceimpl implements  ICourseService{
	private IcourseDao courseDao =  new CourseDaoImp();



	@Override
	public int addcourse(course course ) {
		if (courseDao.checkName(course.getName())) {
			return Constant.ADD_NAME_REPEAT;
		}else {
			int conut = courseDao.addcourse(course);
			if (conut>0) {
				return Constant.ADD_SUCCESS;
			}
		}
		return Constant.ADD_FAIL;
	}

	@Override
	public boolean delete(Integer id) {
		
		return courseDao.delete(id);
	}

	@Override
	public boolean update(course course) {
		if (courseDao.update(course) > 0) {
			return true;
		} 
		
		return false;
	}
	

	@Override
	public course findById(int id) {
		return courseDao.findById(id);
	}

	@Override
	public List<course> findbyname(String name) {
		return courseDao.findbyname(name);
	}










	@Override
	public List <course> findcourse() {
		List<course> list = courseDao.findcourse();
		for (course course : list) {
		}
		return courseDao.findcourse();

	}



	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = courseDao.getTotalCount();

		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int offset = (pageNo - 1) * pageSize;
		List<course> list = courseDao.findPageBeanList(offset, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	
	}

	@Override
	public PageBean<course> searchByCondition(courseSearchCondition courseSearchCondition) {
		PageBean pageBean = new PageBean();
		int pageNo = courseSearchCondition.getPageNo();
		int pageSize = courseSearchCondition.getPageSize();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = courseDao.getTotalCount(courseSearchCondition);
		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		List<course> list = courseDao.findPageBeanList(courseSearchCondition);
		pageBean.setList(list);
		return pageBean;
		
	
	}




	




}
