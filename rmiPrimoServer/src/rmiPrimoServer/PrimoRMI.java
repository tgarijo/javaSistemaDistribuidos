package rmiPrimoServer;

public interface PrimoRMI extends java.rmi.Remote  {
	public boolean primo(long number) throws java.rmi.RemoteException;;
	public void closeRegistry() throws java.rmi.RemoteException;
}

