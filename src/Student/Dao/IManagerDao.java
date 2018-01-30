package Student.Dao;

import java.util.List;
import java.util.Map;

import vo.ManagerSearchCondition;

public interface IManagerDao {

//	List<Map<String, Object>> findAllByJdbc();

	int getTotalCount(ManagerSearchCondition managerSearchCondition);

	List<Map<String, Object>> findPageBeanList(ManagerSearchCondition managerSearchCondition);

}
