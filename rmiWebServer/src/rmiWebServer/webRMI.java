package rmiWebServer;

import java.io.File;
import java.util.List;

public interface webRMI extends java.rmi.Remote {

	boolean consultarWeb(String  url) throws java.rmi.RemoteException;
	boolean borrarDatos() throws java.rmi.RemoteException;
	List<String> consultarWebLinks(String  url) throws java.rmi.RemoteException;
	public void closeRegistry() throws java.rmi.RemoteException;
}
