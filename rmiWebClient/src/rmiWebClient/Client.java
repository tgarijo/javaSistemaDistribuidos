package rmiWebClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmiWebServer.webRMI;



public class Client {
	public static void main(String[] args) {
		

		if(args.length!=1) 
			System.out.println("\n 1: Serializa web, 2: Borra fichero Serializado, 3: Saca las url de le sitio \n");
		
		webRMI server = null;
		
		// Roles de Seguridad
		if(System.getSecurityManager() == null) 
			System.setSecurityManager(new SecurityManager());
		
		try {
			server = (webRMI) Naming.lookup("//localhost:5080/server");
			
			
			
			switch ( args[0]) {
				case "1":
					String url = "https://es.wikipedia.org/wiki/Java_Remote_Method_Invocation";
					
					if( server.consultarWeb(url)) 
						System.out.println("Web consultada y serializada en el disco del servidor\n");
					else  System.out.println("Hubo un error al consultar la web\n");
				break;
				case "2":
					if ( server.borrarDatos() )
						System.out.println("Fichero borrado del servidor\n");
					else System.out.println("Hubo un error al consultar la web\n");
				
				break;
			}
			
			
			
			 
			 
			
			
			
			
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
