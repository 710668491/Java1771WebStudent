package Student.service;

import Student.entity.User;

public interface IUserService {

	User login(String name, String password);

}
