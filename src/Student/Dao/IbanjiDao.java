package Student.Dao;

import java.util.List;

import Student.entity.Student;
import Student.entity.banji;
import vo.StudentSearchCondition;
import vo.banjiSearchCondition;

public interface IbanjiDao {
public abstract int addbanji(banji banji);
	
	public abstract  boolean delete(Integer id);
	
	public abstract int update(banji banji);
	
	public abstract banji findById(Integer id);
	
	public abstract List<banji> findbanji();
	
	public abstract boolean checkName(String name);

	public abstract List<banji> findbyname(String name);

	

	public abstract List<banji> searchByCondition(banjiSearchCondition banjiSearchCondition);

	public abstract int getTotalCount(banjiSearchCondition banjiSearchCondition);

	public abstract List<banji> findPageBeanList(banjiSearchCondition banjiSearchCondition);

	public abstract int getTotalCount();

	public abstract List<banji> findPageBeanList(int offset, int pageSize);



}



