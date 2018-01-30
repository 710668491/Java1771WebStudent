package Student.service.impl;

import java.util.List;

import Student.Dao.IbanjiDao;
import Student.Dao.impl.BanjiDaoImp;
import Student.entity.banji;
import Student.service.IBanjiService;
import Student.until.Constant;
import vo.PageBean;
import vo.banjiSearchCondition;

public class banjiserviceimpl implements IBanjiService{
	private IbanjiDao banjiDao =  new BanjiDaoImp();



	@Override
	public int addbanji(banji banji) {
		if (banjiDao.checkName(banji.getName())) {
			return Constant.ADD_NAME_REPEAT;
		}else {
			int conut = banjiDao.addbanji(banji);
			if (conut>0) {
				return Constant.ADD_SUCCESS;
			}
		}
		return Constant.ADD_FAIL;
	}

	@Override
	public boolean delete(Integer id) {
		
		return banjiDao.delete(id);
	}

	@Override
	public boolean update(banji banji) {
		if (banjiDao.update(banji) > 0) {
			return true;
		} 
		
		return false;
	}
	

	@Override
	public banji findById(int id) {
		return banjiDao.findById(id);
	}

	@Override
	public List<banji> findbyname(String name) {
		return banjiDao.findbyname(name);
	}





	@Override
	public boolean update(String banji) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public List<banji> findbanji() {
		

		return banjiDao.findbanji();

	}

	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = banjiDao.getTotalCount();

		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int offset = (pageNo - 1) * pageSize;
		List<banji> list = banjiDao.findPageBeanList(offset, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	

	}

	@Override
	public PageBean searchByCondition(banjiSearchCondition banjiSearchCondition) {
		PageBean pageBean = new PageBean();
		int pageNo = banjiSearchCondition.getPageNo();
		int pageSize = banjiSearchCondition.getPageSize();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		int totalCount = banjiDao.getTotalCount(banjiSearchCondition);
		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		List<banji> list = banjiDao.findPageBeanList(banjiSearchCondition);
		pageBean.setList(list);
		return pageBean;
		
	}

	@Override
	public boolean checkName(String name) {
		return banjiDao.checkName(name);
	}






	




}
