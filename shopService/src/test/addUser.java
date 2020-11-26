package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DataClass.User;
import Orm.serviceUser;

class addUser {

	@Test
	void test() {
		User user = new User();
		user.setId(2);
		user.setUser("tgarijo2");
		user.setEmail("tgarijo@tgarijo.com");
		user.setPassword("pepito");
		user.setTelefono("91523363369");
		
		serviceUser serviceuser = new serviceUser();
		
		serviceuser.registerNewUser(user);
		
		
		fail("Not yet implemented");
	}

}
