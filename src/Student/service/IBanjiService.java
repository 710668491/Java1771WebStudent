package Student.service;

import java.util.List;

import Student.entity.Student;
import Student.entity.banji;
import vo.PageBean;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;

public interface IBanjiService {



	int addbanji(banji banji);

	banji findById(int id);

	boolean update(String banji);
	
	List<banji> findbyname(String name);



	boolean delete(Integer id);

	boolean update(banji banji);

	List<banji> findbanji();

	PageBean getPageBean(int pageNo, int pageSize);

	PageBean<banji> searchByCondition(banjiSearchCondition banjiSearchCondition);

	boolean checkName(String name);



}
