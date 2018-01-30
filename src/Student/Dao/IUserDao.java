package Student.Dao;

import Student.entity.User;

public interface IUserDao {

	User login(String name, String password);

}
