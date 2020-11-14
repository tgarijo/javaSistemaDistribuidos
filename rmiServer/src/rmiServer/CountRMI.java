package rmiServer;



public interface CountRMI extends java.rmi.Remote 
{
	int sum() throws java.rmi.RemoteException;
	void sum(int val) throws java.rmi.RemoteException;
	public int increment() throws java.rmi.RemoteException;
	public void closeRegistry() throws java.rmi.RemoteException;
	
}
