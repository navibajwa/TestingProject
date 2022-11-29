package org.iiitb.userLogin.startup;

import org.iiitb.userLogin.model.User;
import org.iiitb.userLogin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

	@Autowired
	private UserRepository ur;

	@EventListener(ContextRefreshedEvent.class)
	public void bootUp() {
		System.out.println("Startup Starts");

		User u0 = new User();
		u0.setEmail("aman.gupta@iiitb.ac.in");
		u0.setUserId(1);
		u0.setUsername("bolleyboll");
		u0.setPassword("password");
		u0.setName("Aman Gupta");
		u0.setGroups("1, 2, 3");
		ur.save(u0);

		User u1 = new User();
		u1.setEmail("aman.iv0012@gmail.com");
		u1.setUserId(2);
		u1.setUsername("amang");
		u1.setPassword("password");
		u1.setName("Aman G");
		u1.setGroups("2, 3");
		ur.save(u1);

		User u2 = new User();
		u2.setEmail("aman@gmail.com");
		u2.setUserId(3);
		u2.setUsername("red");
		u2.setPassword("password");
		u2.setName("Smokes");
		u2.setGroups("1, 3");
		ur.save(u2);

		User u3 = new User();
		u3.setEmail("amn@gmail.com");
		u3.setUserId(4);
		u3.setUsername("chief");
		u3.setPassword("password");
		u3.setName("Master Chief");
		u3.setGroups("1, 2, 3");
		ur.save(u3);


		System.out.println("Startup End");

	}
}
