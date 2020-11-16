package rmiPrimoClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmiPrimoServer.PrimoRMI;

public class rmiPrimoClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimoRMI server = null;
		

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
	//	
	//	
		try {
			server = (PrimoRMI) Naming.lookup("//localhost:5090/server");
			
			long number = Long.parseLong(args[0]);
			if(server.primo(number)) 
				System.out.println("\n El número " + number + " es Primo");
			else System.out.println("\n El número " + number + " es NO Primo");
			
			
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nMandado señal de cierre de servidor\n");
		try {			
			server.closeRegistry();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Servidor Cerrado");
		}
			
	}

}
