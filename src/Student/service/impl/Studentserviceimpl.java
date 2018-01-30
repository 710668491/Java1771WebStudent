package Student.service.impl;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import Student.Dao.IStudentDao;
import Student.Dao.impl.StudentDaoImpl;
import Student.entity.Student;
import Student.service.IStudentService;
import Student.until.Constant;
import vo.PageBean;
import vo.StudentSearchCondition;

public class Studentserviceimpl implements IStudentService{
	private IStudentDao studentDao =  new StudentDaoImpl();

	@Override
	public List<Student> findAll() {
		List<Student> list = studentDao.findAll();
		for (Student student : list) {
		}
		return studentDao.findAll();
	}

	@Override
	public int add(Student student) {
		if (studentDao.checkName(student.getName())) {
			return Constant.ADD_NAME_REPEAT;
		}else {
			int conut = studentDao.add(student);
			if (conut>0) {
				return Constant.ADD_SUCCESS;
			}
		}
		return Constant.ADD_FAIL;
	}

	@Override
	public boolean delete(Integer id) {
		int result = studentDao.delete(id);
		if (result >0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean update(Student student) {
		if (studentDao.update(student) > 0) {
			return true;
		} 
		
		return false;
	}
	

	@Override
	public Student findById(int id) {
		return studentDao.findById(id);
	}

	@Override
	public List<Student> findbyname(String name) {
		return studentDao.findbyname(name);
	}

	@Override
	public PageBean searchByCondition(StudentSearchCondition studentSearchCondition) {
		PageBean pageBean = new PageBean();
		int pageNo = studentSearchCondition.getPageNo();
		int pageSize = studentSearchCondition.getPageSize();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = studentDao.getTotalCount(studentSearchCondition);
		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		List<Student> list = studentDao.findPageBeanList(studentSearchCondition);
		pageBean.setList(list);
		return pageBean;
		
	}

	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = studentDao.getTotalCount();

		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int offset = (pageNo - 1) * pageSize;
		List<Student> list = studentDao.findPageBeanList(offset, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public boolean checkName(String name) {
		return studentDao.checkName(name);
	}

	@Override
	public boolean deleteAll(String[] ids) {
		return studentDao.deleteAll(ids);
	}

	
}
