package rmiServer;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends java.rmi.server.UnicastRemoteObject implements CountRMI{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws RemoteException {
		
		// Roles de seguridad
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		 	
		// Inicio servidor RMI
		try {
			RmiServer server = new RmiServer();
			Naming.rebind("//localhost:5060/server", server);
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
	
	private int sum;
	
	final Registry registry = LocateRegistry.createRegistry(5060);
	
	private RmiServer() throws RemoteException {
		super();
		System.out.println("Inicializando Servidor de conteo");
	}
	
	@Override
	public int sum() throws RemoteException {
		return this.sum;
	}

	@Override
	public void sum(int val) throws RemoteException {
		this.sum = val;
		System.out.println("Valor Recibido: " + val);
	}

	@Override
	public int increment() throws RemoteException {
		this.sum++;
		return this.sum;
	}

	@Override
	public void closeRegistry() throws RemoteException {
		System.out.println("Recibiendo señal de cierre...");
		UnicastRemoteObject.unexportObject(registry, true);
		System.exit(1);
	}

}
