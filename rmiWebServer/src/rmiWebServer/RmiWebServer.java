package rmiWebServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RmiWebServer extends java.rmi.server.UnicastRemoteObject implements webRMI
{

	private static final long serialVersionUID = 1L;
	final Registry registry = LocateRegistry.createRegistry(5080);
	

	public static void main(String[] args) 
	{
		// Roles de seguridad
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		 	
		// Inicio servidor RMI
		try {
			
			RmiWebServer server = new RmiWebServer();
			Naming.rebind("//localhost:5080/server", server);
			System.out.println("Iniciando servidor.....");
			
		} catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en RmiServer:");
            e.printStackTrace();
            System.exit(1);
        }		

	}
	
	protected RmiWebServer() throws RemoteException 
	{
		super();		
	}

	private  void escribirFichero(String line,  String nombreFichero) throws IOException 
	{
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero));
	    writer.write(line);
	
	    writer.close();
	}
	

	@Override
	public boolean consultarWeb(String url) throws RemoteException 
	{
		final String fichero = "./data.html";
		String inputLine;
		
		
		BufferedReader in = this.getData(url);
		
		if(in== null ) throw new RuntimeException();	
		
		StringBuilder sb = new StringBuilder();
		
		try 
		{
			
			while ((inputLine = in.readLine()) != null) 
			{
				sb.append(inputLine);
				sb.append(System.lineSeparator());
			}
				
			escribirFichero(sb.toString(), fichero);
			
			in.close();
			
			System.out.println("Fichero " + fichero + " Generado");
			
		} catch (IOException e) {			
			e.printStackTrace();
			return false;
		}
       
		return true;
        
	}

	
	@Override
	public boolean borrarDatos() throws RemoteException 
	{
		final String fichero = "./data.html";
		
		File fd = new File(fichero);
		
		if (fd.delete()) 
		{
			System.out.println("El fichero " + fichero + " ha sido borrado satisfactoriamente");
			return true;
		}else 
		{
			System.out.println("El fichero no puede ser borrado");
			return false;
		}
	}

	@Override
	public List<String> consultarWebLinks(String url) throws RemoteException 
	{
		final String fichero = "./pattern.txt";
		
		String inputLine;
		String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
		
		BufferedReader in = getData(url);
		
		if(in== null ) throw new RuntimeException();
		
		StringBuilder sb = new StringBuilder();
		
		try {
			while ((inputLine = in.readLine()) != null) 
			{
				sb.append(inputLine);
				sb.append(System.lineSeparator());
			}
				
			escribirFichero(sb.toString(), "./pattern.txt");
		
		} catch (IOException e) 
		{			
			e.printStackTrace();
			return null;
		}
		
		return getPattern(patternString);
	}
	
	
	
	private List<String>  getPattern(String patternStr) 
	{
		
 		String filename = "./pattern.txt";	
 		int numPatrones = 0;
 		
	    BufferedReader rd;
	    
	    List<String> setList = new ArrayList<String>();
	    
		try 
		{
			
			rd = new BufferedReader(new FileReader(filename));
			
			Pattern pattern = Pattern.compile(patternStr);
		    Matcher matcher = pattern.matcher("\\D");

		    String line = null;
		    
		    while ((line = rd.readLine()) != null) 
		    {
		      matcher.reset(line);
		      
		      if (matcher.find()) // Encontramos el patron a buscar
		      {		    	  		    	
		    	  if(matcher.group(1).substring(1,5).equals((String) "http" )) // filtramos el patron
		    	  {
		    		  setList.add(matcher.group(1));
		    		  numPatrones++;
		    	  }		    	  
		      }		      		      
			}
		    
		    if (numPatrones>0)		    	  
		    	  System.out.println("Patrones extraidos = " + numPatrones);
		    
		} catch (IOException e) {
			
			e.printStackTrace();			
			return null;
		}
		
		return setList;
	    
	}

	private BufferedReader getData(String url ) 
	{
		URL webUrl;
	
		try 
		{
			webUrl = new URL(url);
			
			BufferedReader in = new BufferedReader(
		        new InputStreamReader(webUrl.openStream()));		        
	        return in;
	        
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	        		
	}
	
	@Override
	public void closeRegistry() throws RemoteException 
	{
		System.out.println("Recibiendo señal de cierre...");
		UnicastRemoteObject.unexportObject(registry, true);
		System.exit(1);
	}

	
}
