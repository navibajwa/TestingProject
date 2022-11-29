package org.iiitb.userLogin.services;

import java.util.List;

import org.iiitb.userLogin.model.User;

public interface UserService {
	User saveUser(User user);

	void delUser(String username);

	User login(String email, String password);

	String[] userGroups(String username);

	List<User> allUsers();
}
