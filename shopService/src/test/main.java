package test;

import DataClass.User;
import Orm.serviceUser;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(1);
		user.setUser("tgarijo");
		user.setEmail("tgarijo@tgarijo.com");
		user.setPassword("pepito");
		user.setTelefono("91523363369");
		
		serviceUser serviceuser = new serviceUser();
		
		serviceuser.registerNewUser(user);
	}

}
