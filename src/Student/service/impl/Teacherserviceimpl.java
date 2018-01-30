package Student.service.impl;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import Student.Dao.IStudentDao;
import Student.Dao.IteacherDao;
import Student.Dao.impl.StudentDaoImpl;
import Student.Dao.impl.TeacherDaoImpl;
import Student.entity.Student;
import Student.entity.teacher;
import Student.service.IStudentService;
import Student.service.ITeacherService;
import Student.until.Constant;
import vo.StudentSearchCondition;
import vo.TeacherSearchCondition;

public class Teacherserviceimpl implements  ITeacherService{
	private IteacherDao teacherDao =  new TeacherDaoImpl();

	@Override
	public List<teacher> findAll() {
		List<teacher> list = teacherDao.findAll();
		for (teacher teacher : list) {
		}
		return teacherDao.findAll();
	}

	@Override
	public int add(teacher teacher) {
		if (teacherDao.checkName(teacher.getName())) {
			return Constant.ADD_NAME_REPEAT;
		}else {
			int conut = teacherDao.add(teacher);
			if (conut>0) {
				return Constant.ADD_SUCCESS;
			}
		}
		return Constant.ADD_FAIL;
	}

	@Override
	public boolean delete(Integer id) {
		
		return teacherDao.delete(id);
	}

	@Override
	public boolean update(teacher teacher) {
		if (teacherDao.update(teacher) > 0) {
			return true;
		} 
		
		return false;
	}
	

	@Override
	public teacher findById(int id) {
		return teacherDao.findById(id);
	}

	@Override
	public List<teacher> findbyname(String name) {
		return teacherDao.findbyname(name);
	}

	@Override
	public List<teacher> searchByCondition(TeacherSearchCondition teacherSearchCondition) {
		
		return teacherDao.searchByCondition(teacherSearchCondition);
	}

	
}
