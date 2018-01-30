package Student.service.impl;

import Student.Dao.IUserDao;
import Student.Dao.impl.UserDaoImpl;
import Student.entity.User;
import Student.service.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new UserDaoImpl();

	@Override
	public User login(String name, String password) {
		return userDao.login(name,password);
	}

}
