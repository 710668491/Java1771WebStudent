package Student.service;

import java.util.List;
import java.util.Map;

import vo.ManagerSearchCondition;
import vo.PageBean;

public interface IManagerService {

	//List<Map<String, Object>> findAll();

	PageBean searchByCondition(ManagerSearchCondition managerSearchCondition);

	
}
