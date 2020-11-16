package rmiPrimoServer;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;




public class PrimoServer extends java.rmi.server.UnicastRemoteObject implements PrimoRMI {

	private static final long serialVersionUID = 1L;
	final Registry registry = LocateRegistry.createRegistry(5090);
	
	public static void main(String[] args) {
		
		// Roles de seguridad
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			 	
			// Inicio servidor RMI
			try {
				
				PrimoServer server = new PrimoServer();
				Naming.rebind("//localhost:5090/server", server);
				System.out.println("Iniciando servidor.....");
				
			}  catch (RemoteException e) {
	            System.err.println("Error de comunicacion: " + e.toString());
	            System.exit(1);
	        }
	        catch (Exception e) {
	            System.err.println("Excepcion en RmiServer:");
	            e.printStackTrace();
	            System.exit(1);
	        }		

	}
	
	protected PrimoServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean primo(long number) {
		
		System.out.println("\n Recibiendo el numero " + number);
		
	    if (number%2==0) return false;
	
	    for(int i=3;i*i<=number;i+=2) {
	        if(number%i==0)
	            return false;
	    }
	    return true;
	}
	
	@Override
	public void closeRegistry() throws RemoteException {
		System.out.println("Recibiendo señal de cierre...");
		UnicastRemoteObject.unexportObject(registry, true);
		System.exit(1);
	}



}
