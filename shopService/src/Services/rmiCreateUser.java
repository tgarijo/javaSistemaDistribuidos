package Services;

import DataClass.User;

public interface rmiCreateUser extends java.rmi.Remote  {
	boolean registerNewUser(User user) throws java.rmi.RemoteException;	
}
