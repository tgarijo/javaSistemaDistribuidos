package rmiClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmiServer.CountRMI;


public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long diferentTime = 0;
		long startTime = 0;
		long stopTime = 0;
		
		CountRMI server = null;
		
		// Roles de Seguridad
		if(System.getSecurityManager() == null) 
			System.setSecurityManager(new SecurityManager());
		
		try {
			server = (CountRMI) Naming.lookup("//localhost:5060/server");
			
			// Estableciendo valor de conteo
			server.sum(0);

			System.out.println("Iniciando conteo\n");
			
			for (int x=0;x<1000;x++) {
			
				startTime = System.nanoTime();
				server.increment();
				stopTime = System.nanoTime();
				
				diferentTime += (stopTime - startTime);
			}
					
			System.out.println(server.sum());
	
			System.out.println("\nFinalizando conteo");
			
			System.out.print("\nTiempo medio: " + diferentTime/1000);
			
			
			
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
