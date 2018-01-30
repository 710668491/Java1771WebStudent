package Student.service.impl;

import java.util.List;
import java.util.Map;

import Student.Dao.IManagerDao;
import Student.Dao.impl.ManagerDaoImpl;
import Student.entity.Student;
import Student.service.IManagerService;
import vo.ManagerSearchCondition;
import vo.PageBean;

public class ManagerServiceImpl implements IManagerService {
	private IManagerDao managerDao = new ManagerDaoImpl();

	/*@Override
	public List<Map<String, Object>> findAll() {
		
		return managerDao.findAllByJdbc();
	}*/

	@Override
	public PageBean searchByCondition(ManagerSearchCondition managerSearchCondition) {
		PageBean pageBean = new PageBean();
		int pageNo = managerSearchCondition.getPageNo();
		int pageSize = managerSearchCondition.getPageSize();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = managerDao.getTotalCount(managerSearchCondition);
		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		List<Map<String, Object>> list = managerDao.findPageBeanList(managerSearchCondition);
		pageBean.setList(list);
		return pageBean;
		
	}


}
